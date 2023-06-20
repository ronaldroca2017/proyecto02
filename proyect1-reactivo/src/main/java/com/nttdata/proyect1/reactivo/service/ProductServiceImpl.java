package com.nttdata.proyect1.reactivo.service;

import com.nttdata.proyect1.reactivo.model.Product;
import com.nttdata.proyect1.reactivo.model.ProductType;
import com.nttdata.proyect1.reactivo.repository.IProductRepository;
import com.nttdata.proyect1.reactivo.repository.IProductTypeRepository;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    IProductRepository productRepository;

    @Autowired
    IProductTypeRepository productTypeRepository;

    @Override
    public Single<Product> save(Product product) {
        return Single.fromPublisher(productRepository.save(product));
    }

    @Override
    public Single<Product> update(Product product) {
        return Single.fromPublisher(productRepository.save(product));
    }

    @Override
    public Observable<Product> findAll() {
        return Observable.fromPublisher(productRepository.findAll());
    }

    @Override
    public Single<Product> findById(String id) {
        return Single.fromPublisher(productRepository.findById(id));
    }

    @Override
    public Single<ProductType> saveProductType(ProductType productType) {
        return Single.fromPublisher(productTypeRepository.save(productType));
    }

    @Override
    public Observable<ProductType> findAllProductType() {
        return Observable.fromPublisher(productTypeRepository.findAll());
    }

    @Override
    public Single<ProductType> findProductTypeByIdProduct(String idProductType) {
        return Single.fromPublisher(productTypeRepository.findProductTypeByIdProduct(idProductType));
    }
}
