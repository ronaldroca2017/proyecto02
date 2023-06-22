package com.nttdata.proyect1.reactivo.repository;

import com.nttdata.proyect1.reactivo.dto.AccountMovementDto;
import com.nttdata.proyect1.reactivo.model.Account;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface IAccountRepository extends ReactiveMongoRepository<Account, String> {


    @Query("{'idClient' : ?0}")
    Flux<Account> findAccountByIdClient(String idClient);

    @Query("{'idProduct' : ?0}")
    Flux<Account> findAccountByIdProduct(String idProduct);

    @Query("{'idClient' : ?0}")
    Flux<Account> consultAvailableBalances(String idClient);

    @Query("{'idClient' : ?0, 'idProducto' : ?1}")
    Flux<AccountMovementDto> consultClientMovement(String idClient, String idProducto);

}
