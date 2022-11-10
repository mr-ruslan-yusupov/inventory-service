package com.inventory.service.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_stores")
public class Store {
    @Id
    //@GeneratedValue
    @SequenceGenerator(name="seq-gen-store",sequenceName="SEQ_GEN_STORE",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen-store")
    @Column(name = "store_id", nullable = false)
    private Long storeId;

    @Column(name = "store_name", nullable = false, unique = true, updatable = false)
    private String storeName;

    @Column(name = "store_address", nullable = false, unique = true, updatable = false)
    private String storeAddress;

    @OneToMany(mappedBy = "store")
    private Set<Item> items;

    public Store() {
    }

    public Store(String storeName, String storeAddress) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store store)) return false;
        return new EqualsBuilder().append(getStoreName(), store.getStoreName()).append(getStoreAddress(), store.getStoreAddress()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getStoreName()).append(getStoreAddress()).toHashCode();
    }
}
