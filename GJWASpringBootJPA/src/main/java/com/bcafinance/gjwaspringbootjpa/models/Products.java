package com.bcafinance.gjwaspringbootjpa.models;

import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Data
@Entity
@Table(name = "MstProduct")
public class Products implements Serializable {
//public class Products implements Serializable {

    private static final long serialversionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ProductCategoryID")
    private ProductCategory productCategory;

    @NotEmpty(message = ConstantMessage.WARNING_PROD_NAME_MANDATORY)
    @Column(name = "ProductName",length = 50,nullable = false,unique = true)
    private String name;

    @NotEmpty(message = ConstantMessage.WARNING_PROD_NAME_MANDATORY)
    @Column(name = "ProductDescription",length = 255,nullable = false)
    private String description;

    @NotNull(message = ConstantMessage.WARNING_PROD_PRICE_MANDATORY)
    @Column(name = "Price",nullable = false)
    private Double price;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    @ManyToMany
    @JoinTable(
            name = "ProductSupplier",
            joinColumns = @JoinColumn(name="ProductID",referencedColumnName = "ProductID"),
            inverseJoinColumns = @JoinColumn(name = "SupplierID",referencedColumnName = "SupplierID")
    )
    @JsonManagedReference
    private Set<Suppliers> suppliers = new HashSet<Suppliers>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Suppliers> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Suppliers> suppliers) {
        this.suppliers = suppliers;
    }
}
