package com.bcafinance.gjwaspringbootjpa.models;

import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name="MstHue")
public class Azim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Long id;

    @Email(message = ConstantMessage.ERROR_EMAIL_FORMAT_INVALID)
    @NotEmpty(message = ConstantMessage.WARNING_CUST_EMAIL_MANDATORY)
    @Column(name = "Email",length = 50 ,nullable = false,unique = true)
    private String email;

    @NotEmpty(message = ConstantMessage.WARNING_CUST_PH_NUMBER_MANDATORY)
    @Column(name = "PhoneNumber", length = 16, nullable = false, unique = true)
    private String phoneNumber;

    @NotEmpty(message = ConstantMessage.WARNING_CUST_FIRSTNAME_MANDATORY)
    @Column(name = "FirstName",length = 20,nullable = false)
    private String firstName;

    @NotNull(message = "TANGGAL TIDAK BOLEH NULL")
    private LocalDate localDate;

    @NotNull(message = "DATA INTEGER TIDAK BOLEH NULL")
    private Double dataInteger;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    //    @Column(name = "CreatedDate",nullable = true, columnDefinition = "datetime2(7) DEFAULT GETDATE() ")
    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    public Azim() {
    }
}
