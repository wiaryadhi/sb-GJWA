package com.bcafinance.sbgjwa.repository;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 22/11/2022 - 14:04
Last Modified on 22/11/2022 - 14:04
Version 1.0
*/
import com.bcafinance.sbgjwa.model.DimEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCDimEmployeeRepository implements DimEmployeeRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(DimEmployee de) {
        return jdbcTemplate.update("INSERT INTO DimEmployee_copy1 (FirstName, LastName, NameStyle, CurrentFlag, SalesPersonFlag) VALUES (?,?,?,?,?)",
                new Object[] {
                        de.getFirstName(),
                        de.getLastname(),
                        de.getNameStyle(),
                        de.getCurrentFlag(),
                        de.getSalesPersonFlag()
                });
    }

    @Override
    public int update(DimEmployee de) {
        return jdbcTemplate.update("UPDATE DimEmployee_copy1 SET FirstName=?,LastName=?,NameStyle=?,CurrentFlag=?,SalesPersonFlag=? WHERE EmployeeKey=?",
                new Object[]{
                        de.getFirstName(),
                        de.getLastname(),
                        de.getNameStyle(),
                        de.getCurrentFlag(),
                        de.getSalesPersonFlag(),
                        de.getEmployeeKey()
                });
    }

    public DimEmployee findById(int id) {
        try{
            DimEmployee dimEmployee = jdbcTemplate.queryForObject("SELECT EmployeeKey, FirstName, LastName, NameStyle, CurrentFlag, SalesPersonFlag FROM DimEmployee_copy1 WHERE EmployeeKey=?",
                    BeanPropertyRowMapper.newInstance(DimEmployee.class), id);
            return dimEmployee;
        } catch (Exception e) {
            System.out.println("Masuk sini"+e.getMessage());
            return null;
        }
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM DimEmployee_copy1 WHERE EmployeeKey=?", id);
    }

    @Override
    public List<DimEmployee> findAll() {
        return jdbcTemplate.query("SELECT * from DimEmployee_copy1", BeanPropertyRowMapper.newInstance(DimEmployee.class));
    }

    @Override
    public List<DimEmployee> findByName(String name) {
        return jdbcTemplate.query("SELECT * from DimEmployee_copy1 WHERE FirstName+LastName LIKE CONCAT('%',?,'%')",
                BeanPropertyRowMapper.newInstance(DimEmployee.class), name);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE FROM DimEmployee_copy1");
    }


}
