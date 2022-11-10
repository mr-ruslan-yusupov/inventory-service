package com.inventory.service.controller;

import com.inventory.service.service.*;
import com.inventory.service.model.Brand;
import com.store.util.StoreConstants;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = {"/","/inventory-service"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String home() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[Application info]");
        sb.append("\nApplication name : " + applicationName);
        sb.append("\nBuild version    : " + buildVersion);
        sb.append("\nBuild timestamp  : " + buildTimestamp);
        return sb.toString();
    }

    @PostMapping(value = "/inventory-service/brand/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addBrand(@RequestBody Brand brand) {
        JSONObject jsonObject = new JSONObject();
        try {
            Brand savedBrand = brandService.saveOrUpdateBrand(brand);
            jsonObject.put("status", StoreConstants.SUCCESS_STATUS);
            jsonObject.put("message", "Brand " + savedBrand.getBrandName() + " saved successfully");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (JSONException e) {
            try {
                jsonObject.put("status", StoreConstants.FAILURE_STATUS);
                jsonObject.put("exception", e.getMessage());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
        }
    }



}
