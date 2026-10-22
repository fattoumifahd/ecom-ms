package com.example.productms.services;

import com.example.productms.exception.EntityNotFoundException;
import com.example.productms.mappers.ProductMapper;
import com.example.productms.models.ProductStatus;
import com.example.productms.models.dtos.Product.CreateProductDTO;
import com.example.productms.models.dtos.Product.UpdateProductDTO;
import com.example.productms.models.dtos.ProductImage.ProductImageDTO;
import com.example.productms.models.entity.Product;
import com.example.productms.models.entity.ProductImage;
import com.example.productms.repo.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceImpl implements IProductService{
    private ProductMapper mapper;
    @Autowired
    private ProductRepo  productRepo;


    @Override
    public Product getProductById(Long id) throws  EntityNotFoundException {
        return productRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Product not found"));
    }

    @Override
    public void updateProduct(Long id, UpdateProductDTO updateProductDTO) throws EntityNotFoundException {
        if(isProductExists(id)){
            Product product = mapper.updateProductMapper(updateProductDTO);
            product.setUpdatedAt(LocalDateTime.now());
            productRepo.save(product);
        } else {
            throw new EntityNotFoundException("Product not found");
        }


    }

    @Override
    public void deleteProductById(Long id) throws EntityNotFoundException {
        if(isProductExists(id)){
            productRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("Product not found");
        }

    }
    @Override
    public Product createProduct(CreateProductDTO productDTO) {

        Product product = mapper.createProductMapper(productDTO);
        product.setCreatedAt(LocalDateTime.now());
        product.setStatus(ProductStatus.ACTIVE);
        productRepo.save(product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return  productRepo.findAll();
    }

    @Override
    public Boolean isProductExists(Long id) {
        return productRepo.existsById(id);
    }

    @Override
    public void addImage(Long productId, ProductImageDTO imageDTO) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        ProductImage productImage = mapper.modelMapper.map(imageDTO, ProductImage.class);
        product.addImage(productImage);
        productRepo.save(product);

    }


}
