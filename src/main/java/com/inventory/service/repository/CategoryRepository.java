package com.inventory.service.repository;

import com.inventory.service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findCategoryByCategoryName(String categoryName);
}
