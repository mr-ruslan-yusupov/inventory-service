package com.inventory.service.service;

import com.inventory.service.model.Product;
import com.inventory.service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductService {
    final private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Collection<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findProductsByCatalogNumber(String catalogNumber) {
        return productRepository.findProductsByCatalogNumber(catalogNumber);
    }

    public List<Product> findProductsByBrand(Long brandId) {
        return productRepository.findProductsByBrand(brandId);
    }

    public List<Product> findProductsByCategory(Long categoryId) {
        return productRepository.findProductsByCategory(categoryId);
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}
