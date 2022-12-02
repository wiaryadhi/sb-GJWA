package com.bcafinance.gjwaspringbootjpa.controllers;


import com.bcafinance.gjwaspringbootjpa.dto.DivisionDTO;
import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.handler.ResponseHandler;
import com.bcafinance.gjwaspringbootjpa.models.Division;
import com.bcafinance.gjwaspringbootjpa.services.DivisionService;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class DivisionController {

    @Getter
    private DivisionService divisionService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @GetMapping("/v1/divisions/{id}")
    public ResponseEntity<Object> getDivisionById(@PathVariable("id") long id) throws Exception {
        Division division = divisionService.findByIdDivision(id);

        if(division != null)
        {
            DivisionDTO divisionDTO = modelMapper.map(division,DivisionDTO.class);

            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,divisionDTO,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }


    @PostMapping("/v1/divisions")
    public ResponseEntity<Object>
    saveDivision(@Valid @RequestBody DivisionDTO divisionDTO
//    saveDivision(@Valid @RequestBody Division division
    ) throws Exception {
        Division division = modelMapper.map(divisionDTO,Division.class);

        divisionService.saveDivision(division);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/divisions/datas/all/31")
    public ResponseEntity<Object> findAllDivision()throws Exception{

        List<Division> lsDivision = divisionService.findAllDivision();

        if(lsDivision.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsDivision,null,null);
    }

    @GetMapping("/v1/divisions/datas/dto/all/24")
    public ResponseEntity<Object> findAllDivisionDTO()throws Exception{

        List<Division> lsDivision = divisionService.findAllDivision();

        if(lsDivision.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<DivisionDTO> lsDivisionDTO = modelMapper.map(lsDivision, new TypeToken<List<DivisionDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsDivisionDTO,null,null);
    }

    @GetMapping("/v1/divisions/datas/search/{name}")
    public ResponseEntity<Object> getDivisionByName(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK, divisionService.findByDivisionName(name),null,null);
    }


    @PutMapping("/v1/divisions/h")
    public ResponseEntity<Object> updateDivisionByID(@Valid @RequestBody DivisionDTO divisionDTO)throws Exception{

        Division division = modelMapper.map(divisionDTO,Division.class);

        divisionService.updateDivisionById(division);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @PostMapping("/v1/divisions/bc")
    public ResponseEntity<Object>
    saveAllDivision(@Valid @RequestBody List<Division> listDivision
    ) throws Exception {
        divisionService.saveAllDivision(listDivision);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

}
