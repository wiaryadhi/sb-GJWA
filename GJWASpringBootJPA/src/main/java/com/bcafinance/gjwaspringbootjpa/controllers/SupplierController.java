package com.bcafinance.gjwaspringbootjpa.controllers;

import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.handler.ResponseHandler;
import com.bcafinance.gjwaspringbootjpa.models.Suppliers;
import com.bcafinance.gjwaspringbootjpa.services.SupplierService;
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
public class SupplierController {

    @Getter
    private SupplierService supplierService;


    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/v1/suppliers/{id}")
    public ResponseEntity<Object> getSupplierById(@PathVariable("id") long id) throws Exception {
        Suppliers suppliers = supplierService.findByIdSuppliers(id);

        if(suppliers != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,suppliers,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @PostMapping("/v1/suppliers")
    public ResponseEntity<Object>
    saveSupplier(@Valid @RequestBody Suppliers suppliers
    ) throws Exception {
        supplierService.saveSuppliers(suppliers);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/suppliers/datas/all/4")
    public ResponseEntity<Object> findAllSupplier()throws Exception{

        List<Suppliers> lsSuppliers = supplierService.findAllSuppliers();

        if(lsSuppliers.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsSuppliers,null,null);
    }

    @GetMapping("/v1/suppliers/datas/search/{name}")
    public ResponseEntity<Object> getSupplierByName(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,supplierService.findBySuppliersName(name),null,null);
    }

    @PutMapping("/v1/suppliers/b")
    public ResponseEntity<Object> updateSupplierByID(@Valid @RequestBody Suppliers suppliers)throws Exception{
        supplierService.updateSuppliersById(suppliers);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @PostMapping("/v1/suppliers/bc")
    public ResponseEntity<Object>
    saveAllSuppliers(@Valid @RequestBody List<Suppliers> listSuppliers
    ) throws Exception {
        supplierService.saveAllProduct(listSuppliers);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }
}
