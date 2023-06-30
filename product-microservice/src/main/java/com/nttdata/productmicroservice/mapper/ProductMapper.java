package com.nttdata.productmicroservice.mapper;

import com.nttdata.productmicroservice.entity.Product;
import com.openapi.gen.springboot.dto.ProductDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements EntityMapper<ProductDTO, Product>{


    @Override
    public Product toEntity(ProductDTO dto) {
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public ProductDTO toDto(Product entity) {
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
