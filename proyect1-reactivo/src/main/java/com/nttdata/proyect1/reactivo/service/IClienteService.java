package com.nttdata.proyect1.reactivo.service;

import com.nttdata.proyect1.reactivo.model.Client;
/*
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
*/

import io.reactivex.Single;
import io.reactivex.Observable;

public interface IClienteService {

    Single<Client> save(Client client);

    Single<Client> update(Client client);

    Observable<Client> findAll();

    Single<Client> findById(String id);

}
