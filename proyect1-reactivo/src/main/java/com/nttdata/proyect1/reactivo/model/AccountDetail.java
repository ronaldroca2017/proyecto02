package com.nttdata.proyect1.reactivo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class AccountDetail {
    @EqualsAndHashCode.Include
    @Id
    private String id;

    @Field
    private String quantity;

    @Field
    private LocalDate dateTransaction;

    @Field
    private String typeMovement;


}
