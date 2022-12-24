package com.inventory.service.repository;

import com.inventory.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findProductsByCatalogNumber(String catalogNumber);

    List<Product> findProductsByBrand(Long brandId);

    List<Product> findProductsByCategory(Long categoryId);

}
