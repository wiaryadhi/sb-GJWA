package com.bcafinance.gjwaspringbootjpa.services;

import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.ProductCategory;
import com.bcafinance.gjwaspringbootjpa.repos.ProductCategoryRepo;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ProductCategoryService {

    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    public ProductCategoryService(ProductCategoryRepo categoryRepo) {
        this.productCategoryRepo = categoryRepo;
    }

    public void saveProductCategory(ProductCategory productCategory) throws Exception{
        productCategoryRepo.save(productCategory);
    }

    public ProductCategory findByIdProductCategory(Long id) throws Exception
    {
        return productCategoryRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public List<ProductCategory> findAllProductCategory()
    {
        return productCategoryRepo.findAll();
    }

    public ProductCategory findByProductCategoryName(String productCategoryName) throws Exception
    {

        return productCategoryRepo.findByName(productCategoryName).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    @Transactional(rollbackFor = {Exception.class})
    public void updateProductCategoryById(ProductCategory pC) throws Exception{
        ProductCategory productCategory = productCategoryRepo.findById(pC.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));

        productCategory.setModifiedBy("1");
        productCategory.setModifiedDate(new Date());

        productCategory.setName(pC.getName());
        productCategory.setDescription(pC.getDescription());
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllProductCategory(List<ProductCategory> ls)
    {
        productCategoryRepo.saveAll(ls);
    }
}
