package com.nttdata.clientmicroservice.service;

import com.nttdata.clientmicroservice.entity.Client;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface IClienteService {

    Single<Client> save(Client client);

    Single<Client> update(Client client);

    Observable<Client> findAll();

    Single<Client> findById(String id);

}
