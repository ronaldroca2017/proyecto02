package com.nttdata.accountmicroservice.business;

import com.nttdata.accountmicroservice.entity.Account;
import com.nttdata.accountmicroservice.entity.AccountMovements;
import com.nttdata.accountmicroservice.entity.ClientDTO;
import com.nttdata.accountmicroservice.repository.AccountRepository;
import com.nttdata.accountmicroservice.response.ApiResponse;
import com.nttdata.accountmicroservice.response.ClientResponse;
import com.nttdata.accountmicroservice.response.ProductResponse;
import com.nttdata.accountmicroservice.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.nttdata.accountmicroservice.util.Constantes.*;


@Service
public class AccountServiceImpl implements  AccountService{

    WebClient webClient = WebClient.create("http://localhost:8080");

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Mono<Account> saveAccount(Mono<Account>  account){

      return account.flatMap(act -> {
            return getTypeClientById(act)
                    .switchIfEmpty(Mono.empty())
                    .flatMap(a -> {
                        if(a.getClientType().equals(PERSON_TYPE_PERSONAL)) {
                            return clientPersonal(act);
                        }else if(a.getClientType().equals(PERSON_TYPE_BUSINESS)
                                && isClientBusinessValidationCurrentAccount(act)){
                            return account.flatMap(accountRepository::save);
                        } else if (a.getClientType().equals(PERSON_VIP_TYPE_PERSONAL)) {
                                if(act.getInitialAmount()>=500){
                                    findById(act.getIdClient()).flatMap(acts -> {
                                        if(acts.getProductType().equals(TARJETA_CREDITO_PERSONAL)){
                                            return account.flatMap(accountRepository::save);
                                        }
                                        return Mono.error(new Exception("No cuenta con una tarjeta de crédito"));
                                    });

                                }else{
                                    return Mono.error(new Exception("El monto para aperturar una cuenta debe ser igual o mayor a 500"));
                                }
                        }else if (a.getClientType().equals(PERSON_MYPE_TYPE_BUSINESS)) {

                                findById(act.getIdClient()).flatMap(acts -> {
                                    if(acts.getProductType().equals(TARJETA_CREDITO_EMPRESARIAL)
                                        && acts.getProductType().equals(CURRENT_ACCOUNT)){
                                        return account.flatMap(accountRepository::save);
                                    }else{
                                        return Mono.error(new Exception("No cuenta con una tarjeta de crédito, ni tampoco con una cuenta corriente" ));
                                    }
                                });

                        }
                        return Mono.error(new Exception("error general"));
            });
        });
    }

    @Override
    public Mono<Account> update(Mono<Account> account) {

    return account.flatMap(act -> {
       return getTypeProductById(act)
               .switchIfEmpty(Mono.empty())
               .flatMap(p -> {
                   if(p.getDescription().equals(CUENTAS_BANCARIAS)){
                       if(act.getMovements().get(0).getTypeMovement().equals(DEPOSITO)){
                            findAll().count().flatMap(c -> {
                                        if(c.intValue() <= MAXIMO_TRANSACCIONES){
                                            act.setAmountUpdated(act.getAmountUpdated() +  Double.parseDouble(act.getMovements().get(0).getQuantity()));
                                        }else{
                                            act.setAmountUpdated(act.getAmountUpdated() +  Double.parseDouble(act.getMovements().get(0).getQuantity()));
                                            act.setCommission(COMISION);
                                        }
                                        return null;
                                    });

                       } else if (act.getMovements().get(0).getTypeMovement().equals(RETIRO)) {
                           findAll().count().flatMap(c -> {
                               if(c.intValue() <= MAXIMO_TRANSACCIONES){
                                   act.setAmountUpdated(act.getAmountUpdated() -  Double.parseDouble(act.getMovements().get(0).getQuantity()));
                               }else{
                                   act.setAmountUpdated(act.getAmountUpdated() -  Double.parseDouble(act.getMovements().get(0).getQuantity()));
                                   act.setCommission(COMISION);
                               }
                               return null;
                           });

                       }
                   }else if(p.getDescription().equals(CREDITOS)){
                       if(act.getMovements().get(0).getTypeMovement().equals(PAGO)){
                           act.setAmountUpdated(act.getAmountUpdated() +  Double.parseDouble(act.getMovements().get(0).getQuantity()));
                       }else if (act.getMovements().get(0).getTypeMovement().equals(CONSUMO)){
                           act.setAmountUpdated(act.getAmountUpdated() -  Double.parseDouble(act.getMovements().get(0).getQuantity()));
                       }
                   }
                   return accountRepository.save(act);
               });
    });


    }

    @Override
    public Flux<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Mono<Account> findById(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public Flux<Account> findAccountByIdClient(String idClient) {
        return accountRepository.findAccountByIdClient(idClient);
    }

    @Override
    public Flux<Account> findAccountByIdProduct(String idProduct) {
        return accountRepository.findAccountByIdProduct(idProduct);
    }

    @Override
    public Flux<Account> consultAvailableBalances(String idClient) {
        return accountRepository.consultAvailableBalances(idClient);
    }

    @Override
    public Flux<AccountMovements> consultClientMovement(String idClient, String idProduct) {
        return accountRepository.consultClientMovement(idClient, idProduct);
    }


    private Mono<Account> clientPersonal(Account account){

        return  Mono.from(findAccountByIdClient(account.getIdClient()))
                .switchIfEmpty(Mono.error(new Exception("NO EXISTE EL CLIENTE")))
                .filter(a -> {
                    if (a != null && a.getProductType() != null) {
                        if (!a.getProductType().equals(account.getProductType()))
                            accountRepository.save(account).subscribe();
                        else
                            Mono.error(new Exception("YA TIENE UNA CUENTA REGISTRADA DEL MISMO TIPO"));
                    }
                    return true;
                })
                .switchIfEmpty(Mono.error(new Exception("YA TIENE UNA CUENTA REGISTRADA DEL MISMO TIPO")));

    }

    private boolean isClientBusinessValidationCurrentAccount(Account account){
        return account.getProductType().equals(Constantes.CURRENT_ACCOUNT);
    }

    private Mono<ClientResponse> getTypeClientById(Account account){
        Mono<ClientResponse> monoClientResponse = webClient.get()
                .uri("/manage/client/{id}", account.getIdClient())
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToMono(ClientResponse.class);
        return monoClientResponse;

    }

    private Mono<ProductResponse> getTypeProductById(Account account){
        Mono<ProductResponse> monoProductResponse = webClient.get()
                .uri("/manager/product/{id}", account.getIdProduct())
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToMono(ProductResponse.class);
        return monoProductResponse;

    }

}
