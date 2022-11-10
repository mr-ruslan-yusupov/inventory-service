package com.inventory.service.repository;

import com.inventory.service.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
    Brand findBrandByBrandName(String brandName);
}
