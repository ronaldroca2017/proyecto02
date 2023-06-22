package com.nttdata.proyect1.reactivo.controller;

import com.nttdata.proyect1.reactivo.model.Product;
import com.nttdata.proyect1.reactivo.model.ProductType;
import com.nttdata.proyect1.reactivo.service.IProductService;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping(value = "/saveProduct")
    public Single<Product> saveProduct(@RequestBody Product product){

        return productService.save(product);
    }

    @PutMapping(value = "/updateProduct")
    public Single<Product> updateProduct(@RequestBody Product product){
        return productService.update(product);
    }

    @GetMapping(value = "/getProducts" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Observable<Product> getProducts(){
        return productService.findAll();
    }

    @GetMapping(path = "/{id}" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Single<Product> getProductById(@PathVariable("id") String id){
        return productService.findById(id);
    }


    @PostMapping(value = "/saveProductType")
    public Single<ProductType> saveProductType(@RequestBody ProductType productType){
        return productService.saveProductType(productType);
    }

    @GetMapping(value = "/getProductsType" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Observable<ProductType> getProductsType(){
        return productService.findAllProductType();
    }
}
