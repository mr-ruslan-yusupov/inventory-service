package com.inventory.service;

import com.inventory.service.model.*;
import com.inventory.service.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class InventoryServiceApplication implements CommandLineRunner {

	final private BrandService brandService;
	final private CategoryService categoryService;
	final private ProductService productService;
	final private ItemService itemService;
	final private StoreService storeService;

	public InventoryServiceApplication(
										BrandService brandService,
										CategoryService categoryService,
										ProductService productService,
										ItemService itemService,
										StoreService storeService
									)
	{
		this.brandService = brandService;
		this.categoryService = categoryService;
		this.productService = productService;
		this.itemService = itemService;
		this.storeService = storeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (brandService.findAllBrands().isEmpty()) {
			Brand brand1 = new Brand("HP");
			brandService.saveOrUpdateBrand(brand1);

			Brand brand2 = new Brand("Samsung");
			brandService.saveOrUpdateBrand(brand2);

			Brand brand3 = new Brand("Logitech");
			brandService.saveOrUpdateBrand(brand3);
		}

		if (categoryService.findAllCategories().isEmpty()) {
			Category category1 = new Category("Desktop Computers");
			categoryService.saveOrUpdateCategory(category1);

			Category category2 = new Category("Smartphones");
			categoryService.saveOrUpdateCategory(category2);

			Category category3 = new Category("Keyboards");
			categoryService.saveOrUpdateCategory(category3);
		}

		if (storeService.findAllStorages().isEmpty()) {
			Store store1 = new Store("Store Haifa","Haifa");
			storeService.saveOrUpdateStorage(store1);

			Store store2 = new Store("Store Tel-aviv","Tel-aviv");
			storeService.saveOrUpdateStorage(store2);

			Store store3 = new Store("Store Jerusalem","Jerusalem");
			storeService.saveOrUpdateStorage(store3);
		}

		if (productService.findAllProducts().isEmpty()) {
			Product product = new Product();
			product.setCategory(categoryService.findCategoryByName("Desktop Computers"));
			product.setBrand(brandService.findBrandByName("HP"));
			product.setProductName("Desktop HP Pavilion Gaming TG01-2005nj");
			product.setProductDescription("מחשב נייח מבית HP בעל מעבד AMD Ryzen 7 5700G, זכרון בנפח 16GB, כונן קשיח מהיר בנפח 1TB SSD ומאיץ גרפי Nvidia GeForce RTX 3060Ti.");
			product.setCatalogNumber("HP-TG01-2005NJ");
			product.setProductPrice(BigDecimal.valueOf(5449));
			productService.saveOrUpdateProduct(product);
		}

		if (itemService.findAllStocks().isEmpty()) {
			Item item = new Item();
			item.setProduct(productService.findProductByCatalogNumber("HP-TG01-2005NJ"));
			item.setStore(storeService.findStoreByName("Store Haifa"));
			item.setQuantity(15);
			itemService.saveOrUpdateStock(item);
		}

	}
}
