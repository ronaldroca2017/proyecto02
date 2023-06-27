package com.nttdata.clientmicroservice.controller;


import com.nttdata.clientmicroservice.entity.Client;
import com.nttdata.clientmicroservice.service.IClienteService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private IClienteService clienteService;


    @PostMapping
    public Single<Client> saveClient(@RequestBody Client dish){

        return clienteService.save(dish);
    }

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

}

