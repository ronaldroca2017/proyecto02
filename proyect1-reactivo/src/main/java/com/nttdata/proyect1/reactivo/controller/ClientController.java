package com.nttdata.proyect1.reactivo.controller;

import com.nttdata.proyect1.reactivo.model.Client;
import com.nttdata.proyect1.reactivo.service.IClienteService;
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

    @GetMapping(path = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<Client> getClientById(@PathVariable("id") String id){
        return clienteService.findById(id);
    }



}
