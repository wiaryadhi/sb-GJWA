package com.bcafinance.gjwaspringbootjpa.controllers;

import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.handler.ResponseHandler;
import com.bcafinance.gjwaspringbootjpa.models.ProductCategory;
import com.bcafinance.gjwaspringbootjpa.services.ProductCategoryService;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class ProductCategoryController {

    @Getter
    private ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/v1/procateg/{id}")
    public ResponseEntity<Object> getProductCategoryById(@PathVariable("id") long id) throws Exception {
        ProductCategory productCategory = productCategoryService.findByIdProductCategory(id);

        if(productCategory != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,productCategory,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }


    @PostMapping("/v1/procateg")
    public ResponseEntity<Object>
    saveProductCategory(@Valid @RequestBody ProductCategory productCategory
                ) throws Exception {
        productCategoryService.saveProductCategory(productCategory);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/procateg/datas/all/17")
    public ResponseEntity<Object> findAllProductCategory()throws Exception{

        List<ProductCategory> lsProductCategory = productCategoryService.findAllProductCategory();

        if(lsProductCategory.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsProductCategory,null,null);
    }

    @GetMapping("/v1/procateg/datas/search/{name}")
    public ResponseEntity<Object> getProductCategoryByName(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,productCategoryService.findByProductCategoryName(name),null,null);
    }


    @PutMapping("/v1/procateg/h")
    public ResponseEntity<Object> updateProductCategoryByID(@Valid @RequestBody ProductCategory productCategory)throws Exception{
        productCategoryService.updateProductCategoryById(productCategory);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @PostMapping("/v1/procateg/bc")
    public ResponseEntity<Object>
    saveAllProductCategory(@Valid @RequestBody List<ProductCategory> listProductCategory
    ) throws Exception {
        productCategoryService.saveAllProductCategory(listProductCategory);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }
}
