package com.nttdata.clientmicroservice.service;

import com.nttdata.clientmicroservice.entity.Client;
import com.nttdata.clientmicroservice.repository.IClientRepository;
import com.openapi.gen.springboot.dto.ClientDTO;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    IClientRepository clientRepository;
    @Override
    public Mono<ClientDTO> save(ClientDTO client) {
        Mono<ClientDTO> save = null;
        try{
             save = clientRepository.save(client);
            System.out.println("inserto -> " + save);
        }catch(Exception e){
            System.out.println("no inserto"  + e.getMessage());
        }
        return save;
    }

    @Override
    public Flux<ClientDTO> findAll() {
        return clientRepository.findAll();
    }

/*
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
*/
}

