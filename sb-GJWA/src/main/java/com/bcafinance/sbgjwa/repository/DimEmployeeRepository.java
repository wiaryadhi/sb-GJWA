package com.bcafinance.sbgjwa.repository;

import com.bcafinance.sbgjwa.model.DimEmployee;

import java.util.List;

/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 22/11/2022 - 14:00
Last Modified on 22/11/2022 - 14:00
Version 1.0
*/

public interface DimEmployeeRepository {

    int save(DimEmployee de);
    int update(DimEmployee de);
    DimEmployee findById(int id);

    /*
        Delete maksud nya adalah mengupdate kolom flag status dirubah menjadi 0
     */
    int deleteById(int id);
    List<DimEmployee> findAll();

    List<DimEmployee> findByName(String name);
    int deleteAll();
}
