package com.inventory.service.service;

import com.inventory.service.model.Product;
import com.inventory.service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService {
    final private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Collection<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductByCatalogNumber(String catalogNumber) {
        return productRepository.findProductByCatalogNumber(catalogNumber);
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }
}
