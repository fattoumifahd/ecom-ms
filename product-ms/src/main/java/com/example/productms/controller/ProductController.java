package com.example.productms.controller;

import com.example.productms.models.dtos.Product.CreateProductDTO;
import com.example.productms.models.dtos.Product.UpdateProductDTO;
import com.example.productms.models.dtos.ProductImage.ProductImageDTO;
import com.example.productms.models.entity.Product;
import com.example.productms.services.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController()
@RequestMapping("/api/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Value("${test.refresh}")
    String test;
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        System.out.println(test);
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
