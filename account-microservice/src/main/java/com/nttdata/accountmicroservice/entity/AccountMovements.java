package com.nttdata.accountmicroservice.entity;


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

public class AccountMovements {
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

