package com.nttdata.proyect1.reactivo.service;

import com.nttdata.proyect1.reactivo.dto.AccountDto;
import com.nttdata.proyect1.reactivo.dto.AccountMovementDto;
import com.nttdata.proyect1.reactivo.model.Account;
import com.nttdata.proyect1.reactivo.model.Client;
import com.nttdata.proyect1.reactivo.model.Product;
import com.nttdata.proyect1.reactivo.repository.IAccountRepository;
/*
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
*/

import com.nttdata.proyect1.reactivo.response.ApiResponse;
import com.nttdata.proyect1.reactivo.util.Constantes;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.Observable;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements  IAccountService{

     List<String> listBankingAccount = List.of("AHORRO", "CUENTA CORRIENTE", "PLAZO FIJO");

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    IClienteService clienteService;

    @Autowired
    IProductService productService;


    @Override
    public Single<ApiResponse> saveAccount(Account account) {
        ApiResponse apiResponse = new ApiResponse();
        if(getClientType(account).equals(Constantes.PERSON_TYPE_PERSONAL)) {
            if (clientPersonalMaxAccount(account) <= 3) {
                accountRepository.save(account).subscribe();
                apiResponse.setCodResultado(Constantes.CODE_SUCCESS);
                apiResponse.setMsgResultado(Constantes.SAVE_SUCCESS);

            } else {
                apiResponse.setCodResultado(Constantes.CODE_ERROR);
                apiResponse.setMsgResultado(Constantes.SAVE_VALIDATION_COUNT_ACCOUNT);

            }
        }else if(getClientType(account).equals(Constantes.PERSON_TYPE_BUSINESS)){
            if (isClientBusinessValidationCurrentAccount(account) == true) {
                accountRepository.save(account).subscribe();
                apiResponse.setCodResultado(Constantes.CODE_SUCCESS);
                apiResponse.setMsgResultado(Constantes.SAVE_SUCCESS);

            } else {
                apiResponse.setCodResultado(Constantes.CODE_ERROR);
                apiResponse.setMsgResultado(Constantes.SAVE_VALIDATION_CURRENT_ACCOUNT);

            }
        }
        return Single.just(apiResponse);
    }

    @Override
    public Single<Account> update(Account account) {

        Product prod = productService.findById(account.getIdProduct()).map(p -> p).blockingGet();

        if(prod.getDescription().equals(Constantes.CUENTAS_BANCARIAS)){
            if(account.getMovements().get(0).getTypeMovement().equals(Constantes.DEPOSITO)){
                account.setAmountUpdated(account.getAmountUpdated() +  Double.parseDouble(account.getMovements().get(0).getQuantity()));
            }else if(account.getMovements().get(0).getTypeMovement().equals(Constantes.RETIRO)){
                account.setAmountUpdated(account.getAmountUpdated() -  Double.parseDouble(account.getMovements().get(0).getQuantity()));
            }
        }else if(prod.getDescription().equals(Constantes.CREDITOS)){
            if(account.getMovements().get(0).getTypeMovement().equals(Constantes.PAGO)){
                account.setAmountUpdated(account.getAmountUpdated() +  Double.parseDouble(account.getMovements().get(0).getQuantity()));
            }else if(account.getMovements().get(0).getTypeMovement().equals(Constantes.CONSUMO)){
                account.setAmountUpdated(account.getAmountUpdated() -  Double.parseDouble(account.getMovements().get(0).getQuantity()));
            }
        }


        return Single.fromPublisher(accountRepository.save(account));
    }

    @Override
    public Observable<Account> findAll() {

        return Observable.fromPublisher(accountRepository.findAll());
    }

    @Override
    public Single<Account> findById(String id) {

        return Single.fromPublisher(accountRepository.findById(id));
    }

    @Override
    public Observable<Account> findAccountByIdClient(String idClient) {
        return Observable.fromPublisher(accountRepository.findAccountByIdClient(idClient));
    }

    @Override
    public Observable findAccountByIdProduct(String idProduct) {
        return Observable.fromPublisher(accountRepository.findAccountByIdProduct(idProduct));
    }

    @Override
    public Observable<AccountDto> consultAvailableBalances(String idClient) {

        Observable<AccountDto> observableAccount = Observable.fromPublisher(accountRepository.consultAvailableBalances(idClient)
                .map(account -> {
                            AccountDto accountDto = new AccountDto();
                            accountDto.setId(account.getId());
                            accountDto.setAccountNumber(account.getAccountNumber());
                            accountDto.setProductType(account.getProductType());
                            accountDto.setInitialAmount(account.getInitialAmount());
                            accountDto.setAmountUpdated(account.getAmountUpdated());
                            return accountDto;
                        }
                ));
        return observableAccount;
    }

    @Override
    public Observable<AccountMovementDto> consultClientMovement(String idClient, String idProducto) {
        Observable<AccountMovementDto> observableAccountMovement = Observable.fromPublisher(accountRepository.consultClientMovement(idClient, idProducto)
                .map(accountMovement -> {
                            AccountMovementDto accountMovementDto = new AccountMovementDto();
                            accountMovementDto.setId(accountMovement.getId());
                            accountMovementDto.setQuantity(accountMovement.getQuantity());
                            accountMovementDto.setDateTransaction(accountMovement.getDateTransaction());
                            accountMovementDto.setTypeMovement(accountMovement.getTypeMovement());
                            return accountMovementDto;
                        }
                ));
        return observableAccountMovement;
    }

    private Long clientPersonalMaxAccount(Account account){

        Observable<Account> accountByIdClient = findAccountByIdClient(account.getIdClient());
        Long countAccountClient = accountByIdClient.filter(a -> a.getProductType().equals(account.getProductType())).count().blockingGet();

        return countAccountClient;
    }

    private boolean isClientBusinessValidationCurrentAccount(Account account){
        return account.getProductType().equals(Constantes.CURRENT_ACCOUNT);
    }

    private String getClientType(Account account){
        return  clienteService.findById(account.getIdClient()).map(Client::getClientType).blockingGet();

    }

}
