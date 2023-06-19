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

@Document(collection = "products")
public class product {

    @EqualsAndHashCode.Include
    @Id
    private String id;

    private String description;

    private ProductType productType;
}
