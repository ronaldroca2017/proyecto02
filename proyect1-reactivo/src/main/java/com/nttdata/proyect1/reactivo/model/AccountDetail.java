package com.nttdata.proyect1.reactivo.model;

import lombok.*;
import org.springframework.data.annotation.Id;

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

    private String quantity;



}
