package com.bcafinance.gjwaspringbootjpa.services;

import com.bcafinance.gjwaspringbootjpa.handler.FormatValidation;
import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.Employee;
import com.bcafinance.gjwaspringbootjpa.repos.EmployeeRepo;
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
public class EmployeeService {

    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> findAllEmployee()
    {
        return employeeRepo.findAll();
    }

    public Employee findByIdEmployee(Long id) throws Exception
    {
        return employeeRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public Employee findByEmailEmployee(String email) throws Exception
    {
        return employeeRepo.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_EMPL_NOT_FOUND));
    }

    public void saveEmployee(Employee employee) throws Exception
    {
        FormatValidation.phoneNumberFormatValidation(employee.getPhoneNumber());
        FormatValidation.emailFormatValidation(employee.getEmail());
        FormatValidation.dateFormatYYYYMMDDValidation(employee.getBirthDate().toString());

        Optional<Employee> employeeByEmail = employeeRepo.findByEmail(employee.getEmail());
        if(employeeByEmail.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
        }
        employeeRepo.save(employee);
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public void updateEmployeeById(Employee e) throws Exception{

        Employee employee = employeeRepo.findById(e.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_EMPL_NOT_FOUND));

        employee.setModifiedBy("1");
        employee.setModifiedDate(new Date());
        employee.setFirstName(e.getFirstName());//BERARTI ADA PERUBAHAN DI SINI
        if(e.getMiddleName() != null
                && !e.getMiddleName().equals(""))
        {
            employee.setMiddleName(e.getMiddleName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(e.getEmail().length()>3 && !Objects.equals(e.getEmail(),employee.getEmail()))
        {
            FormatValidation.emailFormatValidation(e.getEmail());
            Optional<Employee> em = employeeRepo.findByEmail(e.getEmail());
            if(em.isPresent())//it means if exists
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
            }
            employee.setEmail(e.getEmail());
        }

        if(e.getAddress() != null
                && !e.getAddress().equals(""))
        {
            employee.setAddress(e.getAddress());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(e.getPhoneNumber().length()>9 &&
                !Objects.equals(employee.getPhoneNumber(),e.getPhoneNumber())){

            FormatValidation.phoneNumberFormatValidation(e.getPhoneNumber());
            employee.setPhoneNumber(e.getPhoneNumber());
        }

        FormatValidation.dateFormatYYYYMMDDValidation(employee.getBirthDate().toString());
        employee.setBirthDate(e.getBirthDate());
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllEmployee(List<Employee> ls)
    {
        employeeRepo.saveAll(ls);
    }
}
