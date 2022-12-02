package com.bcafinance.gjwaspringbootjpa.services;

import com.bcafinance.gjwaspringbootjpa.handler.FormatValidation;
import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.Azim;
import com.bcafinance.gjwaspringbootjpa.repos.AzimRepo;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AzimService {

    private AzimRepo azimRepo;

    @Autowired
    public AzimService(AzimRepo azimRepo) {
        this.azimRepo = azimRepo;
    }

    public void saveAzim(Azim azim) throws Exception
    {
        FormatValidation.phoneNumberFormatValidation(azim.getPhoneNumber());

        Optional<Azim> azimByEmail = azimRepo.findByEmail(azim.getEmail());
        if(azimByEmail.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
        }
        azimRepo.save(azim);
    }


}
