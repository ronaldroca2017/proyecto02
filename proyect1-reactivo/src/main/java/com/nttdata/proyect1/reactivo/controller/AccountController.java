package com.nttdata.proyect1.reactivo.controller;


import com.nttdata.proyect1.reactivo.model.Account;
import com.nttdata.proyect1.reactivo.model.Client;
import com.nttdata.proyect1.reactivo.service.IAccountService;
/*
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;*/

import io.reactivex.Single;
import io.reactivex.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    IAccountService accountService;

    @PostMapping
    public Single<Account> saveClient(@RequestBody Account account){
        return accountService.save(account);
    }

    @PutMapping
    public Single<Account> updateClient(@RequestBody Account account){
        return accountService.update(account);
    }
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Observable<Account> getClients(){
        return accountService.findAll();
    }

    @GetMapping(path = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<Account> getClientById(@PathVariable("id") String id){
        return accountService.findById(id);
    }
}
