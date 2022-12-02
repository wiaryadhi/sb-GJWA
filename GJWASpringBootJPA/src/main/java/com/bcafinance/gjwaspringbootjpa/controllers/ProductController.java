package com.bcafinance.gjwaspringbootjpa.controllers;

import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.handler.ResponseHandler;
import com.bcafinance.gjwaspringbootjpa.models.Products;
import com.bcafinance.gjwaspringbootjpa.models.Suppliers;
import com.bcafinance.gjwaspringbootjpa.services.ProductService;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ProductController {


    @Getter
    private ProductService productService;

    public ProductController() {
    }


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/v1/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") long id) throws Exception {
            Products products = productService.findByIdProduct(id);

            if(products != null)
            {
                return new ResponseHandler().
                        generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,products,null,null);
            }
            else
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
            }
    }

    @PostMapping("/v1/products")
    public ResponseEntity<Object>
    saveProduct(@Valid @RequestBody Products products,
                @RequestHeader Map<String,String> headers,
                @RequestParam Map<String,String> params,
                WebRequest request,Error error) throws Exception {
        productService.saveProduct(products);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/products/datas/all/0")
    public ResponseEntity<Object> findAllProducts()throws Exception{

        int data = 0;
        Iterable<Products> lsProducts = productService.findAllProducts();

        if(lsProducts instanceof Collection<Products>)
        {
            data = ((Collection<Products>) lsProducts).size();
        }
        if(data==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsProducts,null,null);
    }

    @GetMapping("/v1/products/datas/search/{name}")
    public ResponseEntity<Object> getProductByName(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,productService.findByNameProduct(name),null,null);
    }

    @PutMapping("/v1/products/u")
    public ResponseEntity<Object> updateProductByID(@RequestBody Products products)throws Exception{
        productService.updateProducts(products);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @PostMapping("/v1/products/sup/{id}")
    public void addSupplier(@RequestBody Suppliers suppliers,@PathVariable("id") Long productId) throws Exception {
        productService.addSupplier(suppliers,productId);
    }

    @PostMapping("/v1/products/bc")
    public ResponseEntity<Object>
    saveAllProductCategory(@Valid @RequestBody List<Products> listProduct
    ) throws Exception {
        productService.saveAllProduct(listProduct);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }
}