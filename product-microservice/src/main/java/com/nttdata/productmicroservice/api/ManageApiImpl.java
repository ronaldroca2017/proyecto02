package com.nttdata.productmicroservice.api;

import com.nttdata.productmicroservice.business.ProductService;
import com.nttdata.productmicroservice.mapper.ProductMapper;
import com.openapi.gen.springboot.api.ManagerApi;
import com.openapi.gen.springboot.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.nttdata.productmicroservice.util.constantes.Constantes.*;

@RestController
public class ManageApiImpl implements ManagerApi {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> saveProduct(Mono<ProductDTO> productDTO, ServerWebExchange exchange) {
        Map<String, Object> response = new HashMap<>();

        return productService.saveProduct(productDTO.map(productMapper::toEntity))
                .map(productMapper::toDto)
                .map(client -> {
                    response.put(PRODUCT_RESPONSE, client);
                    response.put(MESSAGE, MESSAGE_SAVE);
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                });
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> updateProduct(String id, Mono<ProductDTO> productDTO, ServerWebExchange exchange) {
        Map<String, Object> response = new HashMap<>();

        return productService.updateProduct(id, productDTO.map(productMapper::toEntity))
                .map(productMapper::toDto)
                .map(client -> {
                    response.put(PRODUCT_RESPONSE, client);
                    response.put(MESSAGE, MESSAGE_UPDATE);
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Flux<ProductDTO>>> getProducts(ServerWebExchange exchange) {
        Flux<ProductDTO> fluxProductDto = productService.findAll().map(productMapper::toDto);
        return Mono.just(fluxProductDto)
                .map(ResponseEntity.ok()::body)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<ProductDTO>> getProductById(String id, ServerWebExchange exchange) {
        return productService.findById(id)
                .map(productMapper::toDto)
                .map(ResponseEntity.ok()::body)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
