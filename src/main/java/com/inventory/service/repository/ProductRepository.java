package com.inventory.service.repository;

import com.inventory.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findProductByCatalogNumber(String catalogNumber);
}
