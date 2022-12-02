package com.bcafinance.gjwaspringbootjpa.models;

import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Data
@Entity
@Table(name = "MstSupplier")
public class Suppliers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplierID")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_SUPPLIER_NAME_MANDATORY)
    @Column(name = "SupplierName",length = 50,nullable = false)
    private String name;

    @NotEmpty(message = ConstantMessage.WARNING_SUPPLIER_DESC_MANDATORY)
    @Column(name = "SupplierAddress",length = 255,nullable = false)
    private String address;

    @NotEmpty(message = ConstantMessage.WARNING_SUPPLIER_EMAIL_MANDATORY)
    @Column(name = "SupplierEmail",length = 40,nullable = false)
    private String email;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    @ManyToMany(mappedBy = "suppliers")//suppliers adalah variabel yang dibuat di class Products
    @JsonBackReference
    private Set<Products> productsList = new HashSet<Products>();

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(Set<Products> productsList) {
        this.productsList = productsList;
    }
}