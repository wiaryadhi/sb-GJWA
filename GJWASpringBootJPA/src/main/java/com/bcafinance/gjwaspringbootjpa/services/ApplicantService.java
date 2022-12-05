package com.bcafinance.gjwaspringbootjpa.services;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 05/12/2022 - 11:31
Last Modified on 05/12/2022 - 11:31
Version 1.0
*/
import com.bcafinance.gjwaspringbootjpa.dto.ApplicantDTO;
import com.bcafinance.gjwaspringbootjpa.models.Applicants;
import com.bcafinance.gjwaspringbootjpa.repos.ApplicantRepo;
import com.bcafinance.gjwaspringbootjpa.utils.CsvReader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
@Transactional
public class ApplicantService {

    @Getter
    private ApplicantRepo applicantRepo;

    @Autowired
    public ApplicantService(ApplicantRepo applicantRepo) {
        this.applicantRepo = applicantRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Applicants>saveBulkApplicant(MultipartFile multipartFile) throws Exception
    {
        try{
            List<Applicants> lsApplicant = CsvReader.csvtoApplicantData(multipartFile.getInputStream());
            return applicantRepo.saveAll(lsApplicant);
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public List<Applicants> findAllApplicant()
    {
        return (List<Applicants>)applicantRepo.findAll();
    }

    public Iterable<Applicants> pagingFindAplicantByFirstName(String firstName, Pageable pageable)
    {
        return applicantRepo.findApplicantsByFirstNameContainingIgnoreCase(firstName,pageable);
    }
}
