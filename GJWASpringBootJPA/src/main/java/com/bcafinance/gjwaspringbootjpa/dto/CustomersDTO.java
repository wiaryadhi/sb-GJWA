package com.bcafinance.gjwaspringbootjpa.dto;


import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class CustomersDTO {

    @Length(max = 255,message = ConstantMessage.WARNING_CUST_ADDRESS_MAX_LENGTH)
    private String address;

    @Length(max = 50,message = ConstantMessage.WARNING_CUST_MAX_LENGTH_FIRSTNAME)
    @NotEmpty(message = ConstantMessage.WARNING_CUST_FIRSTNAME_MANDATORY)
    private String firstName;

    @Length(max = 50,message = ConstantMessage.WARNING_CUST_MAX_LENGTH_MIDDLENAME)
    private String middleName;

    @Length(max = 50,message = ConstantMessage.WARNING_CUST_MAX_LENGTH_LASTNAME)
    private String lastName;

    @NotEmpty(message = ConstantMessage.WARNING_SUPPLIER_EMAIL_MANDATORY)
    @Email(message = ConstantMessage.ERROR_EMAIL_FORMAT_INVALID)
    private String email;

}
