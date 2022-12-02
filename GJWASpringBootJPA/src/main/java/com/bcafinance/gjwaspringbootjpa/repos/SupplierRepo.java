package com.bcafinance.gjwaspringbootjpa.repos;

import com.bcafinance.gjwaspringbootjpa.models.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepo extends JpaRepository<Suppliers,Long> {

//    Optional<Suppliers> findBySuppliersName(String supplierName);
    Optional<Suppliers> findByName(String name);
}
