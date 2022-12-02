package com.bcafinance.gjwaspringbootjpa.services;

import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.Products;
import com.bcafinance.gjwaspringbootjpa.models.Suppliers;
import com.bcafinance.gjwaspringbootjpa.repos.ProductRepo;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProductService {

    @Getter
    private ProductRepo productRepo;

    @Autowired
    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void saveProduct(Products products) throws Exception{

        productRepo.save(products);
    }
    public Products findByIdProduct(Long id) throws Exception
    {
//        int intK = 5/0;
        return productRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_PRODUCT_NOT_FOUND));
    }
    public Iterable<Products> findAllProducts()throws Exception{
        return productRepo.findAll();
    }

    public void deleteByIdProduct(Long id) throws Exception{
        productRepo.deleteById(id);
    }

    public Products findByNameProduct(String name) throws Exception
    {
        return productRepo.findProductByName(name).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_PRODUCT_NOT_FOUND));
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public void updateProducts(Products p) throws Exception{

        Products products = productRepo.findById(p.getId()).orElseThrow(()->
                    new ResourceNotFoundException(ConstantMessage.WARNING_PRODUCT_NOT_FOUND));

        products.setModifiedBy("1");
        products.setModifiedDate(new Date());
        if(p.getName() != null && !Objects.equals(products.getName(),p.getName()) && !p.getName().equals(""))
        {
            products.setName(p.getName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(p.getDescription() != null && !Objects.equals(products.getDescription(),p.getDescription()) && !p.getDescription().equals(""))
        {
            products.setDescription(p.getDescription());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(!(p.getPrice()<=((1.0/2) * products.getPrice())) && p.getPrice()!=0 && !(p.getPrice()>(3*products.getPrice())))
        {
            products.setPrice(p.getPrice());
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_PRODUCT_PRICE_SOP);
        }
    }

    public void addSupplier(Suppliers suppliers,Long productId) throws Exception {
        Products products = productRepo.findById(productId).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_PRODUCT_NOT_FOUND));
        products.getSuppliers().add(suppliers);
        saveProduct(products);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllProduct(List<Products> ls)
    {
        productRepo.saveAll(ls);
    }
}