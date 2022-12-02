package com.bcafinance.gjwaspringbootjpa.repos;

import com.bcafinance.gjwaspringbootjpa.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmail(String email);

}