package com.bcafinance.sbgjwa.controller;

import com.bcafinance.sbgjwa.model.DimEmployee;
import com.bcafinance.sbgjwa.repository.DimEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 22/11/2022 - 14:10
Last Modified on 22/11/2022 - 14:10
Version 1.0
*/
@RestController
@RequestMapping("/arya/v1")

public class DimEmployeeController {

    @Autowired
    DimEmployeeRepository dimEmployeeRepository;


    @GetMapping("/dimemployee/{id}")
    public ResponseEntity<DimEmployee> getDimEmployeeById(@PathVariable("id") int id) {
        DimEmployee dimEmployee = dimEmployeeRepository.findById(id);

        if (dimEmployee != null) {
            return new ResponseEntity<>(dimEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dimemployee")
    public ResponseEntity<String> createDimEmployee(@RequestBody DimEmployee dimEmployee) {
        try {
            dimEmployeeRepository.save(new DimEmployee(dimEmployee.getFirstName(),
                    dimEmployee.getLastname(),
                    dimEmployee.getNameStyle(),
                    dimEmployee.getCurrentFlag(),
                    dimEmployee.getSalesPersonFlag()));
            return new ResponseEntity<>("Data berhasil dibuat.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dimemployee/{id}")
    public ResponseEntity<String> editDimEmployee(@PathVariable("id") int id, @RequestBody DimEmployee dimEmployee) {
        DimEmployee putDimEmployee = dimEmployeeRepository.findById(id);
        try {
            if (putDimEmployee != null) {
                putDimEmployee.setEmployeeKey(id);
                putDimEmployee.setFirstName(dimEmployee.getFirstName());
                putDimEmployee.setLastname(dimEmployee.getLastname());
                putDimEmployee.setNameStyle(dimEmployee.getNameStyle());
                putDimEmployee.setCurrentFlag(dimEmployee.getCurrentFlag());
                putDimEmployee.setSalesPersonFlag(dimEmployee.getSalesPersonFlag());

                dimEmployeeRepository.update(putDimEmployee);
                return new ResponseEntity<>("Data Berhasil diperbaharui.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Tidak dapat menemukan data dengan id=" + id, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            System.out.println("masuk exception disini" + e.getMessage());
                return new ResponseEntity<>("Datanya tidak bisa di update", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dimemployee/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
        try {
            int result = dimEmployeeRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Data dengan id " + id +" Tidak ada !!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Data dengan id "+id+" Berhasil di hapus", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Tidak dapat menghapus data .", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dimemployee/6832BLE")
    public ResponseEntity<String> deleteAll() {
        try {
            int numRows = dimEmployeeRepository.deleteAll();
            return new ResponseEntity<>("Berhasil Menghapus  " + numRows + " Data .", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Tidak dapat menghapus data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dimemployee/datas/{name}")
    public ResponseEntity<List<DimEmployee>> findByCustomerName(@PathVariable("name") String name) {
        try {
            List<DimEmployee> listDimEmployee = dimEmployeeRepository.findByName(name);

            if (listDimEmployee.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listDimEmployee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dimemployee/all")
    public ResponseEntity<List<DimEmployee>> findAll(){
        try{
            List<DimEmployee> allDimEmployee = dimEmployeeRepository.findAll();
            if (allDimEmployee.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(allDimEmployee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
