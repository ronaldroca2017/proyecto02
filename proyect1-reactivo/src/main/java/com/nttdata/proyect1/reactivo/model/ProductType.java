package com.nttdata.proyect1.reactivo.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class ProductType {
    @EqualsAndHashCode.Include
    @Id
    private String id;

    private String description;
}
