package com.bcafinance.gjwaspringbootjpa.models;


import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

//@Data
@Entity
@Table(name = "MstProductCategory")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductCategoryID")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_PROD_CATEG_NAME_MANDATORY)
    @Column(name = "ProductCategoryName",length = 50,nullable = false)
    private String name;

    @NotEmpty(message = ConstantMessage.WARNING_PROD_CATEG_DESC_MANDATORY)
    @Column(name = "ProductCategoryDescription",length = 255,nullable = false)
    private String description;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

//    @OneToMany
//    private List<Products> products;

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}