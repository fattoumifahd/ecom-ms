package com.example.productms.repo;

import com.example.productms.models.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepo extends JpaRepository<ProductImage,Long> {

}
