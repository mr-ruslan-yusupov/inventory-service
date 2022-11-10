package com.inventory.service.service;

import com.inventory.service.model.Item;
import com.inventory.service.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemService {
    final private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Collection<Item> findAllStocks() {
        return itemRepository.findAll();
    }

    public Item saveOrUpdateStock(Item item) {
        return itemRepository.saveAndFlush(item);
    }

}
