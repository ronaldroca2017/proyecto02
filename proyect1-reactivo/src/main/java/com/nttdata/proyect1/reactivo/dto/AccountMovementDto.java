package com.nttdata.proyect1.reactivo.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Getter
@Setter
public class AccountMovementDto {

    private String id;

    private String quantity;

    private LocalDate dateTransaction;

    private String typeMovement;


}
