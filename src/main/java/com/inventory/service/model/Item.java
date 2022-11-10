package com.inventory.service.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "tbl_items")
public class Item {
    @EmbeddedId
    ItemCompositeKey id = new ItemCompositeKey();

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name = "store_id", nullable = false, updatable = false)
    private Store store;

    private int quantity;

    public ItemCompositeKey getId() {
        return id;
    }

    public void setId(ItemCompositeKey id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return new EqualsBuilder().append(getProduct(), item.getProduct()).append(getStore(), item.getStore()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getProduct()).append(getStore()).toHashCode();
    }
}
