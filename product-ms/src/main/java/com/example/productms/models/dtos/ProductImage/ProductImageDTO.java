package com.example.productms.models.dtos.ProductImage;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductImageDTO {
    private String url;
    private String altText;
    private Long productId;
}
