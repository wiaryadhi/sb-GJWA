package com.bcafinance.gjwaspringbootjpa.dto;

import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
public class AzimDatax {

    private Long id;

    @Email(message = ConstantMessage.ERROR_EMAIL_FORMAT_INVALID)
    @NotEmpty(message = ConstantMessage.WARNING_CUST_EMAIL_MANDATORY)
    private String email;

    @NotEmpty(message = ConstantMessage.WARNING_CUST_PH_NUMBER_MANDATORY)
    private String phoneNumber;

    @NotEmpty(message = ConstantMessage.WARNING_CUST_FIRSTNAME_MANDATORY)
    private String firstName;

    @NotNull(message = "TANGGAL TIDAK BOLEH NULL")
    private LocalDate localDate;

    @NotNull(message = "DATA INTEGER TIDAK BOLEH NULL")
    private Double dataInteger;

    private String createdBy = "1";

    private Date createdDate = new Date();

    private String modifiedBy ;

    private Date modifiedDate;

    private boolean isActive = true;

    public AzimDatax() {
    }
}
