package com.inventory.service.service;

import com.inventory.service.model.Category;
import com.inventory.service.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoryService {
    final private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Collection<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByCategoryName(categoryName);
    }

    public Category saveOrUpdateCategory(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

}
