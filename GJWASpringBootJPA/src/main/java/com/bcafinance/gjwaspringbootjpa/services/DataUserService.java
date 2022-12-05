package com.bcafinance.gjwaspringbootjpa.services;


import com.bcafinance.gjwaspringbootjpa.configuration.ConfigProperties;
import com.bcafinance.gjwaspringbootjpa.core.Crypto;
import com.bcafinance.gjwaspringbootjpa.core.SMTPCore;
import com.bcafinance.gjwaspringbootjpa.handler.FormatValidation;
import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.DataUsers;
import com.bcafinance.gjwaspringbootjpa.repos.DataUserRepo;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DataUserService {

    private DataUserRepo dataUserRepo;

    @Autowired
    public DataUserService(DataUserRepo userRepo) {
        this.dataUserRepo = userRepo;
    }

    public List<DataUsers> findAllUsers() {
        return dataUserRepo.findAll();
    }

    public DataUsers findByIdUsers(Long id) throws Exception {
        return dataUserRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public List<DataUsers> findFullNameContaining(String fullname) {
        return dataUserRepo.findByUsernameContaining(fullname);
    }

    public List<DataUsers> findTokenLike(String token) {
        return dataUserRepo.findUsersByTokenLike(token);
    }

    public void saveUsers(DataUsers dataUsers) throws Exception {
        if (dataUsers.getUsername() == null)
            throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if (dataUsers.getBirthDate() == null)
            throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if (dataUsers.getEmail() == null) throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if (dataUsers.getPassword() == null)
            throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        FormatValidation.emailFormatValidation(dataUsers.getEmail());
        FormatValidation.dateFormatYYYYMMDDValidation(dataUsers.getBirthDate().toString());

        Optional<DataUsers> userByEmail = dataUserRepo.findByEmail(dataUsers.getEmail());
        if (userByEmail.isPresent()) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
        }
        String eml = dataUsers.getEmail();
        List<String> emly = new ArrayList<String>(Arrays.asList(eml.split(",")));
        String[] strArr = new String[emly.size()];

        String verifCode = new Crypto().performEncrypt(dataUsers.getUsername() + dataUsers.getPassword().toLowerCase() + dataUsers.getNip().toString()).substring(10, 19);
        dataUsers.setToken(verifCode);

        dataUserRepo.save(dataUsers);

        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = emly.get(i);
        }
        SMTPCore sc = new SMTPCore();
        ConfigProperties.getEmailPassword();
        System.out.println(sc.sendMailWithAttachment(strArr,
                "Email Verification", "SILAHKAN CEK EMAIL YANG TELAH ANDA DAFTARKAN : http://localhost:8080/api/v1/a/" + verifCode,
                "SSL"
        ));
    }

}