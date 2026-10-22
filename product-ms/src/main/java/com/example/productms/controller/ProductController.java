package com.example.productms.controller;

import com.example.productms.models.dtos.Product.CreateProductDTO;
import com.example.productms.models.dtos.Product.UpdateProductDTO;
import com.example.productms.models.dtos.ProductImage.ProductImageDTO;
import com.example.productms.models.entity.Product;
import com.example.productms.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<Product> getProduct(Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editProduct(@PathVariable Long id, @RequestBody UpdateProductDTO productDTO) {
        productService.updateProduct(id,productDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Validated @RequestBody CreateProductDTO createProductDTO) {
        return ResponseEntity.ok(productService.createProduct(createProductDTO));
    }
}
