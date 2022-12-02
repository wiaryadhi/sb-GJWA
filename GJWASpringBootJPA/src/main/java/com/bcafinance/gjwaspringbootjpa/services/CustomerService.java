package com.bcafinance.gjwaspringbootjpa.services;


import com.bcafinance.gjwaspringbootjpa.handler.FormatValidation;
import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.Customers;
import com.bcafinance.gjwaspringbootjpa.repos.CustomerRepo;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customers> findAllCustomers()
    {
        return customerRepo.findAll();
    }

    public Customers findByIdCustomers(Long id) throws Exception
    {
        return customerRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public Customers findByEmailCustomers(String email) throws Exception
    {

        return customerRepo.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_CUSTOMER_NOT_FOUND));
    }

    public void saveCustomers(Customers customers) throws Exception
    {
        FormatValidation.phoneNumberFormatValidation(customers.getPhoneNumber());
        FormatValidation.emailFormatValidation(customers.getEmail());
        FormatValidation.dateFormatYYYYMMDDValidation(customers.getBirthDate().toString());

        Optional<Customers> custByEmail = customerRepo.findByEmail(customers.getEmail());
        if(custByEmail.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
        }
        customerRepo.save(customers);
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public void updateCustomerById(Customers c) throws Exception{

        Customers customers = customerRepo.findById(c.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_CUSTOMER_NOT_FOUND));

        customers.setModifiedBy("1");
        customers.setModifiedDate(new Date());
        customers.setFirstName(c.getFirstName());//BERARTI ADA PERUBAHAN DI SINI
        if(c.getMiddleName() != null
                && !c.getMiddleName().equals(""))
        {
            customers.setMiddleName(c.getMiddleName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getEmail().length()>3 && !Objects.equals(c.getEmail(),customers.getEmail()))
        {
            FormatValidation.emailFormatValidation(c.getEmail());
            Optional<Customers> cBeanOptional = customerRepo.findByEmail(c.getEmail());
            if(cBeanOptional.isPresent())//it means if exists
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
            }
            customers.setEmail(c.getEmail());
        }

        if(c.getAddress() != null
                && !c.getAddress().equals(""))
        {
            customers.setAddress(c.getAddress());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getPhoneNumber().length()>9 &&
                !Objects.equals(customers.getPhoneNumber(),c.getPhoneNumber())){

            FormatValidation.phoneNumberFormatValidation(c.getPhoneNumber());
            customers.setPhoneNumber(c.getPhoneNumber());
        }

        FormatValidation.dateFormatYYYYMMDDValidation(customers.getBirthDate().toString());
        customers.setBirthDate(c.getBirthDate());
    }
}