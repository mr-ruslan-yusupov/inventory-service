package com.inventory.service.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_categories")
public class Category {
    @Id
    //@GeneratedValue
    @SequenceGenerator(name="seq-gen-category",sequenceName="SEQ_GEN_CATEGORY",initialValue = 1,allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen-category")
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "category_name", nullable = false, unique = true, updatable = false)
    private String categoryName;

    @OneToMany(targetEntity = Product.class, mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Product> products;

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return new EqualsBuilder().append(getCategoryName(), category.getCategoryName()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getCategoryName()).toHashCode();
    }
}
