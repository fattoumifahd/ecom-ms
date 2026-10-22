package com.example.productms.models.entity;

import com.example.productms.models.ProductStatus;
import com.example.productms.models.dtos.ProductImage.ProductImageDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String sku;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true, length = 255)
    private String description;
    @Column(nullable = false)
    private Double price;
    private String currency;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    private Long categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private List<ProductImage> image;

    public void addImage(ProductImage image) {
        this.image.add(image);
    }

}
