package com.inventory.service.repository;

import com.inventory.service.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
    Store findStoreByStoreName(String storeName);
}
