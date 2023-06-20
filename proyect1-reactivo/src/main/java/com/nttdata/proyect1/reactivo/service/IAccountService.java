package com.nttdata.proyect1.reactivo.service;

import com.nttdata.proyect1.reactivo.model.Account;

/*/
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
*/

import io.reactivex.Single;
import io.reactivex.Observable;
import reactor.core.publisher.Mono;

public interface IAccountService {

    Single<Account> save(Account client);

    Single<Account> update(Account client);

    Observable<Account> findAll();

    Single<Account> findById(String id);

    Observable<Account> findAccountByIdClient(String idClient);

    Observable<Account> findAccountByIdProduct(String idProduct);
}
