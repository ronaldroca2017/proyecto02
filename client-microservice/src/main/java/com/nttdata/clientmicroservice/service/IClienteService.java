package com.nttdata.clientmicroservice.service;


import com.openapi.gen.springboot.dto.ClientDTO;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClienteService {

    Mono<ClientDTO> save(ClientDTO client);

    Flux<ClientDTO> findAll();
/*
    Single<Client> update(Client client);

    Observable<Client> findAll();

    Single<Client> findById(String id);
*/
}
