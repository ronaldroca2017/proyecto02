package com.nttdata.proyect1.reactivo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {

    private String id;

    private String accountNumber;

    private Double initialAmount;

    private Double amountUpdated;

    private Double commission;

    private String idClient;

    private String idProduct;

    private String productType;


}
