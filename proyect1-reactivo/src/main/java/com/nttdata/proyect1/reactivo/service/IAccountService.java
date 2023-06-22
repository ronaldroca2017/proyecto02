package com.nttdata.proyect1.reactivo.service;

import com.nttdata.proyect1.reactivo.dto.AccountDto;
import com.nttdata.proyect1.reactivo.dto.AccountMovementDto;
import com.nttdata.proyect1.reactivo.model.Account;

/*/
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
*/

import com.nttdata.proyect1.reactivo.response.ApiResponse;
import io.reactivex.Single;
import io.reactivex.Observable;

public interface IAccountService {



    Single<ApiResponse> saveAccount(Account account);

    Single<Account> update(Account account);

    Observable<Account> findAll();

    Single<Account> findById(String id);

    Observable<Account> findAccountByIdClient(String idClient);

    Observable<Account> findAccountByIdProduct(String idProduct);

    Observable<AccountDto> consultAvailableBalances(String idClient);

    Observable<AccountMovementDto> consultClientMovement(String idClient, String idProducto);


}
