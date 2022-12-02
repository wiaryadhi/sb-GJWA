package com.bcafinance.gjwaspringbootjpa.services;

import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.Division;
import com.bcafinance.gjwaspringbootjpa.repos.DivisionRepo;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class DivisionService {

    private DivisionRepo divisionRepo;

    @Autowired
    public DivisionService(DivisionRepo categoryRepo) {
        this.divisionRepo = categoryRepo;
    }

    public void saveDivision(Division division) throws Exception{
        divisionRepo.save(division);
    }

    public Division findByIdDivision(Long id) throws Exception
    {
        return divisionRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public List<Division> findAllDivision()
    {
        return divisionRepo.findAll();
    }

    public Division findByDivisionName(String divisionName) throws Exception
    {

        return divisionRepo.findByName(divisionName).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    @Transactional(rollbackFor = {Exception.class})
    public void updateDivisionById(Division pC) throws Exception{
        Division Division = divisionRepo.findById(pC.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));

        Division.setModifiedBy("1");
        Division.setModifiedDate(new Date());

        Division.setName(pC.getName());
        Division.setDescription(pC.getDescription());
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllDivision(List<Division> ls)
    {
        divisionRepo.saveAll(ls);
    }
}