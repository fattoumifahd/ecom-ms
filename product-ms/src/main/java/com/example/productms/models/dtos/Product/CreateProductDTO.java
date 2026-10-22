package com.example.productms.models.dtos.Product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Data
public class CreateProductDTO {
    @Valid
    @NotNull
    private String name;
    @Length(min = 20)
    private String description;
    @NotNull
    private String sku;
    @NotNull
    @Length(min = 3, max = 3)
    private String currency;
    @NotNull
    private Double price;

}
