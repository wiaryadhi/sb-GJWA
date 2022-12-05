package com.bcafinance.gjwaspringbootjpa.repos;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 05/12/2022 - 11:28
Last Modified on 05/12/2022 - 11:28
Version 1.0
*/
import com.bcafinance.gjwaspringbootjpa.models.Applicants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ApplicantRepo extends JpaRepository<Applicants,Long> {
    Page<Applicants> findApplicantsByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
}
