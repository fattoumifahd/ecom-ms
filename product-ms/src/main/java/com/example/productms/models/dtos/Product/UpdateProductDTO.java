package com.example.productms.models.dtos.Product;

import com.example.productms.models.ProductStatus;
import lombok.Data;

@Data
public class UpdateProductDTO {
    private Double price;
    private String sku;
    private ProductStatus status;
    private String description;
}
