package com.bcafinance.gjwaspringbootjpa.repos;

import com.bcafinance.gjwaspringbootjpa.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory,Long> {

    Optional<ProductCategory> findByName(String name);
}