package com.bcafinance.gjwaspringbootjpa.controllers;

import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.handler.ResponseHandler;
import com.bcafinance.gjwaspringbootjpa.models.Customers;
import com.bcafinance.gjwaspringbootjpa.services.CustomerService;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/")
public class CustomerController {

    @Getter
    private CustomerService customerService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/v1/customers")
    public ResponseEntity<Object>
    saveCustomer(@Valid @RequestBody Customers customers) throws Exception {
        customerService.saveCustomers(customers);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/customers/{id}")
    public ResponseEntity<Object> getCutomersById(@PathVariable("id") long id) throws Exception {
        Customers customers = customerService.findByIdCustomers(id);

        if(customers != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,customers,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/customers/datas/all/0")
    public ResponseEntity<Object> findAllCustomers()throws Exception{

//        int data = 0;
        List<Customers> lsCustomers = customerService.findAllCustomers();

        if(lsCustomers.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsCustomers,null,null);
    }

    @GetMapping("/v1/customers/datas/search/{email}")
    public ResponseEntity<Object> getCustomerByEmail(@PathVariable("email") String email)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,customerService.findByEmailCustomers(email),null,null);
    }

    @PutMapping("/v1/customers/u")
//    public ResponseEntity<Object> updateCustomerByID(@Valid @RequestBody Customers customers)throws Exception{
    public ResponseEntity<Object> updateCustomerByID(@Valid @RequestBody Customers customers)throws Exception{
        customerService.updateCustomerById(customers);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }
}