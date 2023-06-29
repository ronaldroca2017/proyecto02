package com.nttdata.clientmicroservice.controller;


import com.nttdata.clientmicroservice.entity.Client;
import com.nttdata.clientmicroservice.service.IClienteService;
import com.openapi.gen.springboot.api.ManagerApi;
import com.openapi.gen.springboot.dto.ClientDTO;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
public class ClientController implements ManagerApi {



    @Autowired
    private IClienteService clienteService;

    @Override
    public Mono<ResponseEntity<ClientDTO>> saveClient(Mono<ClientDTO> clientDTO, ServerWebExchange exchange) {

        return clientDTO.flatMap(c -> {
            return clienteService.save(c);
        }).map(ResponseEntity.ok()::body);
    }

    @Override
    public Mono<ResponseEntity<Flux<ClientDTO>>> getClients(ServerWebExchange exchange) {
        Flux<ClientDTO> listClients = clienteService.findAll();

        return Mono.just(listClients).map(ResponseEntity.ok()::body);
    }

    /*
    @PostMapping
    public Mono<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO){

        return clienteService.save(clientDTO);
    }*/
/*
    @PutMapping
    public Single<Client> updateClient(@RequestBody Client dish){

        return clienteService.update(dish);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Observable<Client> getClients(){

        return clienteService.findAll();
    }

    @GetMapping(path = "/{id}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Single<Client> getClientById(@PathVariable("id") String id){

        return clienteService.findById(id).flatMap(e -> Single.just(e));
    }
*/
}

