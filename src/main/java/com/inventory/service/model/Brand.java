package com.inventory.service.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_brands")
public class Brand {
    @Id
    //@GeneratedValue
    @SequenceGenerator(name="seq-gen-brand",sequenceName="SEQ_GEN_BRAND",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen-brand")
    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "brand_name", nullable = false, unique = true, updatable = false)
    private String brandName;

    @OneToMany(targetEntity = Product.class, mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Product> products;

    public Brand() {
    }

    public Brand(String brandName) {
        this.brandName = brandName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brand brand)) return false;
        return new EqualsBuilder().append(getBrandName(), brand.getBrandName()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getBrandName()).toHashCode();
    }

}
