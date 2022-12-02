package com.bcafinance.gjwaspringbootjpa.services;

import com.bcafinance.gjwaspringbootjpa.handler.FormatValidation;
import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.Suppliers;
import com.bcafinance.gjwaspringbootjpa.repos.SupplierRepo;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class SupplierService {

    private SupplierRepo supplierRepo;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    public void saveSuppliers(Suppliers suppliers) throws Exception{

        FormatValidation.emailFormatValidation(suppliers.getEmail());
        supplierRepo.save(suppliers);
    }

    public Suppliers findByIdSuppliers(Long id) throws Exception
    {
        return supplierRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public List<Suppliers> findAllSuppliers()
    {
        return supplierRepo.findAll();
    }

    public Suppliers findBySuppliersName(String suppliersName) throws Exception
    {

        return supplierRepo.findByName(suppliersName).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public void updateSuppliersById(Suppliers s) throws Exception{
        Suppliers suppliers = supplierRepo.findById(s.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));

        suppliers.setModifiedBy("1");
        suppliers.setModifiedDate(new Date());

        suppliers.setName(s.getName());
        FormatValidation.emailFormatValidation(s.getEmail());
        suppliers.setEmail(s.getEmail());
        suppliers.setAddress(s.getAddress());
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllProduct(List<Suppliers> ls)
    {
        supplierRepo.saveAll(ls);
    }
}
