package com.example.productms.models.dtos.Product;

import com.example.productms.models.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDTO {
    private Double price;
    private String sku;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    private String description;
}
