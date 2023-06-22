package com.nttdata.proyect1.reactivo.controller;


import com.nttdata.proyect1.reactivo.dto.AccountDto;
import com.nttdata.proyect1.reactivo.dto.AccountMovementDto;
import com.nttdata.proyect1.reactivo.model.Account;
import com.nttdata.proyect1.reactivo.response.ApiResponse;
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
    public Single<ApiResponse> saveAccount(@RequestBody Account account){
        return accountService.saveAccount(account);
    }

    @PutMapping
    public Single<Account> saveAccountMovements(@RequestBody Account account){
        return accountService.update(account);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Observable<Account> getAccounts(){
        return accountService.findAll();
    }

    @GetMapping(path = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<Account> getClientById(@PathVariable("id") String id){
        return accountService.findById(id);
    }

    @GetMapping(value = "/getAvailableBalances/{idClient}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Observable<AccountDto> getAvailableBalances(@PathVariable("idClient") String idClient){
        return accountService.consultAvailableBalances(idClient);
    }

    @GetMapping(value = "/getAvailableBalances/{idClient}/{idProdcut}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Observable<AccountMovementDto> getAvailableBalances(
                                                    @PathVariable("idClient") String idClient,
                                                    @PathVariable("idProdcut") String idProdcut){
        return accountService.consultClientMovement(idClient, idProdcut);
    }

}
