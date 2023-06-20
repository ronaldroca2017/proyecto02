package com.nttdata.proyect1.reactivo.service;

import com.nttdata.proyect1.reactivo.model.Account;
import com.nttdata.proyect1.reactivo.repository.IAccountRepository;
/*
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
*/

import io.reactivex.Single;
import io.reactivex.Observable;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements  IAccountService{


    @Autowired
    IAccountRepository accountRepository;

    @Override
    public Single<Account> save(Account account) {

        return Single.fromPublisher(accountRepository.save(account));
    }

    @Override
    public Single<Account> update(Account account) {

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


}
