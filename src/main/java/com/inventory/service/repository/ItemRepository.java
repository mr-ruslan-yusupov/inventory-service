package com.inventory.service.repository;

import com.inventory.service.model.Item;
import com.inventory.service.model.ItemCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, ItemCompositeKey> {
}
