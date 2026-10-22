package com.example.productms.mappers;

import com.example.productms.models.dtos.Product.CreateProductDTO;
import com.example.productms.models.dtos.Product.UpdateProductDTO;
import com.example.productms.models.entity.Product;
import org.modelmapper.ModelMapper;


public class ProductMapper {

    public final ModelMapper modelMapper = new ModelMapper();
    public Product createProductMapper(CreateProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);

    }

    public Product updateProductMapper(UpdateProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
