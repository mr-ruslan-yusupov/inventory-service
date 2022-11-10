package com.inventory.service.service;

import com.inventory.service.model.Store;
import com.inventory.service.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StoreService {
    final private StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Collection<Store> findAllStorages() {
        return storeRepository.findAll();
    }

    public Store findStoreByName(String storeName) {
        return storeRepository.findStoreByStoreName(storeName);
    }

    public Store saveOrUpdateStorage(Store store) {
        return storeRepository.saveAndFlush(store);
    }

}
