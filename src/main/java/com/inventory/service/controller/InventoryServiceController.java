package com.inventory.service.controller;

import com.inventory.service.exceptions.*;
import com.inventory.service.model.*;
import com.inventory.service.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryServiceController {
    final private BrandService brandService;
    final private CategoryService categoryService;
    final private StoreService storeService;
    final private ProductService productService;
    final private ItemService itemService;

    @Value("${application.name}")
    private String applicationName;

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimestamp;

    public InventoryServiceController(BrandService brandService,
                                      CategoryService categoryService,
                                      StoreService storeService,
                                      ProductService productService,
                                      ItemService itemService)
    {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.storeService = storeService;
        this.productService = productService;
        this.itemService = itemService;
    }

    @RequestMapping(value = {"/","/inventory"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String home() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[Application info]");
        sb.append("\nApplication name : " + applicationName);
        sb.append("\nBuild version    : " + buildVersion);
        sb.append("\nBuild timestamp  : " + buildTimestamp);
        return sb.toString();
    }

    /***********/
    /** BRAND **/
    /***********/

    @PostMapping(value = "/inventory/brand/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brand> addBrand(@RequestBody Brand brand) {
        Brand savedBrand = brandService.saveOrUpdateBrand(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(brand);
    }

    @PutMapping(value = "/inventory/brand/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand) {
        Brand savedBrand = brandService.saveOrUpdateBrand(brand);
        return ResponseEntity.ok().body(brand);
    }

    @DeleteMapping(value = "/inventory/brand/delete/{brandId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteBrand(@PathVariable Long brandId) {
        List<Product> productsByBrand = productService.findProductsByBrand(brandId);
        if (!CollectionUtils.isEmpty(productsByBrand)) {
            throw new BrandNotEmptyException("Can't delete brand with id " + brandId + ", brand has products.");
        }
        try {
            brandService.deleteBranch(brandId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new BrandNotFoundException("Not found brand with id: " + brandId);
        }
        return ResponseEntity.ok().build();
    }

    /**************/
    /** CATEGORY **/
    /**************/

    @PostMapping(value = "/inventory/category/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveOrUpdateCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping(value = "/inventory/category/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveOrUpdateCategory(category);
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping(value = "/inventory/category/delete/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        List<Product> productsByCategory = productService.findProductsByCategory(categoryId);
        if (!CollectionUtils.isEmpty(productsByCategory)) {
            throw new CategoryNotEmptyException("Can't delete category with id " + categoryId + ", category has products.");
        }
        try {
            categoryService.deleteCategory(categoryId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new CategoryNotFoundException("Not found category with id: " + categoryId);
        }
        return ResponseEntity.ok().build();
    }

    /***********/
    /** STORE **/
    /***********/

    @PostMapping(value = "/inventory/store/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Store> addStore(@RequestBody Store store) {
        Store savedStore = storeService.saveOrUpdateStore(store);
        return ResponseEntity.status(HttpStatus.CREATED).body(store);
    }

    @PutMapping(value = "/inventory/store/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Store> updateStore(@RequestBody Store store) {
        Store savedStore = storeService.saveOrUpdateStore(store);
        return ResponseEntity.ok().body(store);
    }

    @DeleteMapping(value = "/inventory/store/delete/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteStore(@PathVariable Long storeId) {
        List<Item> itemsByStore = itemService.findItemsByStore(storeId);
        if (!CollectionUtils.isEmpty(itemsByStore)) {
            throw new StoreNotEmptyException("Can't delete store with id " + storeId + ", store has products.");
        }
        try {
            storeService.deleteStore(storeId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new StoreNotFoundException("Not found store with id: " + storeId);
        }
        return ResponseEntity.ok().build();
    }

}
