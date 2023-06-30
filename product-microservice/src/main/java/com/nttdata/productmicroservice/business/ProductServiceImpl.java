package com.nttdata.productmicroservice.business;

import com.nttdata.productmicroservice.entity.Product;
import com.nttdata.productmicroservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ProductServiceImpl implements  ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Mono<Product> saveProduct(Mono<Product> product) {
        return product.flatMap(productRepository::save);
    }

    @Override
    public Mono<Product> updateProduct(String id, Mono<Product> product) {
        return productRepository.findById(id)
                .flatMap(c -> product)
                .doOnNext(e -> e.setIdProduct(id))
                .flatMap(productRepository::save);
    }

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> findById(String idProduct) {
        return productRepository.findById(idProduct);
    }
}
