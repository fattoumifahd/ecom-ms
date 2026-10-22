package com.example.productms.services;

import com.example.productms.models.dtos.ProductImage.ProductImageDTO;
import com.example.productms.models.entity.ProductImage;

public interface IProductImageService {
    public void deleteProductImage(Long id);
    public ProductImage getProductImageById(Long id);
    public ProductImage updateProductImage(Long imageId,ProductImageDTO imageDTO);
//    public ProductImage createProductImage(ProductImageDTO imageDTO);
}
