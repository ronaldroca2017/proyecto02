package com.nttdata.proyect1.reactivo.service;

import com.nttdata.proyect1.reactivo.model.Product;
import com.nttdata.proyect1.reactivo.model.ProductType;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface IProductService {

    Single<Product> save(Product product);

    Single<Product> update(Product product);

    Observable<Product> findAll();

    Single<Product> findById(String id);


    Single<ProductType> saveProductType(ProductType productType);

    Observable<ProductType> findAllProductType();
    Single<ProductType> findProductTypeByIdProduct(String idProductType);

}
