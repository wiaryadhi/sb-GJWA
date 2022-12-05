package com.bcafinance.gjwaspringbootjpa.controllers;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 05/12/2022 - 11:45
Last Modified on 05/12/2022 - 11:45
Version 1.0
*/

import com.bcafinance.gjwaspringbootjpa.dto.ApplicantDTO;
import com.bcafinance.gjwaspringbootjpa.dto.MovieGenreDTO;
import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.handler.ResponseHandler;
import com.bcafinance.gjwaspringbootjpa.models.Applicants;
import com.bcafinance.gjwaspringbootjpa.models.MovieDirectors;
import com.bcafinance.gjwaspringbootjpa.services.ApplicantService;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import com.bcafinance.gjwaspringbootjpa.utils.CsvReader;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class ApplicantController {

    @Getter
    private ApplicantService applicantService;
    @Autowired
    private ModelMapper modelMapper;

    private List<Applicants> lsApplicant = new ArrayList<Applicants>();

    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping("/v1/applicant/upl/bat/11")
    public ResponseEntity<Object>
    uploadApplicant(@Valid @RequestParam("demoFile") MultipartFile multipartFile) throws Exception {
//        try{
//            if(CsvReader.isCsv(multipartFile))
//            {
//                applicantService.saveBulkApplicant(multipartFile);
////                return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,
////                        HttpStatus.CREATED,null,null,null);
//            }
//            else
//            {
//                throw new ResourceNotFoundException(ConstantMessage.ERROR_NOT_CSV_FILE+" -- "+multipartFile.getOriginalFilename());
//            }
//        }catch (Exception e)
//        {
//            throw new Exception(ConstantMessage.ERROR_UPLOAD_CSV+multipartFile.getOriginalFilename());
//        }

        if(CsvReader.isCsv(multipartFile))
        {
            applicantService.saveBulkApplicant(multipartFile);
        }
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,
                        HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/applicant/datas/all/dto/9")
    public ResponseEntity<Object> findAllCitizenDTO()throws Exception {

        List<Applicants> lsApplicant = applicantService.findAllApplicant();

        if(lsApplicant.size()!=0)
        {
            List<ApplicantDTO> lsApplicantDTO = modelMapper.map(lsApplicant, new TypeToken<List<ApplicantDTO>>() {}.getType());

            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsApplicantDTO,null,null);
        }
        throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
    }

    @GetMapping("/v1/applicant/search/dto/{size}/{page}")
    public ResponseEntity<Object> pageFindApplicantByNameDTO(@RequestParam String firstName,
                                                           @PathVariable("size") int size,
                                                           @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,applicantService.pagingFindAplicantByFirstName(firstName,pageable),null,null);
    }

    @GetMapping("/v1/applicant/search/dto/{size}/{page}/{sort}")
    public ResponseEntity<Object> pageSortByNameDTO(@RequestParam String firstName,
                                                    @PathVariable("size") int size,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("sort") String sortz)throws Exception {

        Pageable pageable;
        if(sortz.equalsIgnoreCase("desc"))
        {
            pageable = PageRequest.of(page,size, Sort.by("AppId").descending());
        }
        else
        {
            pageable = PageRequest.of(page,size, Sort.by("AppId"));//default asc
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,applicantService.pagingFindAplicantByFirstName(firstName,pageable),null,null);
    }
}
