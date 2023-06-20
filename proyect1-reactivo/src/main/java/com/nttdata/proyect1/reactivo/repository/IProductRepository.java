package com.nttdata.proyect1.reactivo.repository;

import com.nttdata.proyect1.reactivo.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProductRepository  extends ReactiveMongoRepository<Product, String> {
}
