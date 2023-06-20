package com.nttdata.proyect1.reactivo.repository;

import com.nttdata.proyect1.reactivo.model.Account;
import com.nttdata.proyect1.reactivo.model.ProductType;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IProductTypeRepository extends ReactiveMongoRepository<ProductType, String> {

    @Query("{'idProductType' : ?0}")
    Mono<ProductType> findProductTypeByIdProduct(String idProductType);
}
