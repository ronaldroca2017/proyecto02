package com.nttdata.clientmicroservice.service;

import com.nttdata.clientmicroservice.entity.Client;
import com.nttdata.clientmicroservice.repository.IClientRepository;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    IClientRepository clientRepository;
    @Override
    public Single<Client> save(Client client) {
        return Single.fromPublisher(clientRepository.save(client));
    }

    @Override
    public Single<Client> update(Client client) {
        return Single.fromPublisher(clientRepository.save(client));
    }

    @Override
    public Observable<Client> findAll() {
        return Observable.fromPublisher(clientRepository.findAll());
    }

    @Override
    public Single<Client> findById(String id) {
        return Single.fromPublisher(clientRepository.findById(id));
    }

}

