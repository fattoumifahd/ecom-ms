package com.example.productms.services;

import com.example.productms.exception.EntityNotFoundException;
import com.example.productms.models.dtos.ProductImage.ProductImageDTO;
import com.example.productms.models.entity.ProductImage;
import com.example.productms.repo.ProductImageRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageServiceImpl implements IProductImageService{
    @Autowired
    private ProductImageRepo productImageRepo;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public void deleteProductImage(Long id) {
        if(productImageRepo.existsById(id)){
            productImageRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Product Image not found");
        }
    }

    @Override
    public ProductImage getProductImageById(Long id) {
       return productImageRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product Image not found"));
    }

    @Override
    public ProductImage updateProductImage(Long imageId, ProductImageDTO imageDTO) {
        ProductImage image = productImageRepo.findById(imageId).orElseThrow(() -> new EntityNotFoundException("Product Image not found"));
        image.setAltText(imageDTO.getAltText());
        image.setUrl(imageDTO.getUrl());
        productImageRepo.save(image);
        return image;

    }

//    @Override
//    public ProductImage createProductImage(ProductImageDTO imageDTO) {
//        ProductImage image = new ProductImage();
//        image.setAltText(imageDTO.getAltText());
//        image.setUrl(imageDTO.getUrl());
////        image.setProduct(imageDTO.getProductId());
//    }
}
