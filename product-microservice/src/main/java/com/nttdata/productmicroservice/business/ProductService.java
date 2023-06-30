package com.nttdata.productmicroservice.business;

import com.nttdata.productmicroservice.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> saveProduct(Mono<Product> product);

    Mono<Product> updateProduct(String id, Mono<Product> product);

    Flux<Product> findAll();

    Mono<Product> findById(String idProduct);
}
