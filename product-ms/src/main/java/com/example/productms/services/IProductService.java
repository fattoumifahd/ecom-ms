package com.example.productms.services;

import com.example.productms.models.dtos.Product.CreateProductDTO;
import com.example.productms.models.dtos.Product.UpdateProductDTO;
import com.example.productms.models.dtos.ProductImage.ProductImageDTO;
import com.example.productms.models.entity.Product;
import com.example.productms.models.entity.ProductImage;

import java.util.List;

public interface IProductService {
    public Product getProductById(Long id);
    public void updateProduct(Long id, UpdateProductDTO updateProductDTO);
    public void deleteProductById(Long id);
    public Product createProduct(CreateProductDTO productDTO);
    public List<Product> getAllProducts();
    public Boolean isProductExists(Long id);
    public void addImage(Long productId, ProductImageDTO productImage);
}
