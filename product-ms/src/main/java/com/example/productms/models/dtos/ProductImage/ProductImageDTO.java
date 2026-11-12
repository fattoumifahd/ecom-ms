package com.example.productms.models.dtos.ProductImage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductImageDTO {
    private String url;
    private String altText;
    private Long productId;
}
