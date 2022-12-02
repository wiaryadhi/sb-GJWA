package com.bcafinance.gjwaspringbootjpa.models;


import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Data
@Entity
@Table(name = "MstEmployee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private Long id;

    @ManyToOne
    private Division division;

    @Length(message = ConstantMessage.WARNING_EMPL_MAX_LENGTH_EMAIL)
    @NotEmpty(message = ConstantMessage.WARNING_EMPL_EMAIL_MANDATORY)
    @Column(name = "Email",length = 50 ,nullable = false,unique = true)
    private String email;

    @Length(message = ConstantMessage.WARNING_EMPL_MAX_LENGTH_PHONE)
    @NotEmpty(message = ConstantMessage.WARNING_EMPL_PH_NUMBER_MANDATORY)
    @Column(name = "PhoneNumber", length = 16, nullable = false, unique = true)
    private String phoneNumber;

    @Length(max = 20, message = ConstantMessage.WARNING_EMPL_MAX_LENGTH_FIRSTNAME)
    @NotEmpty(message = ConstantMessage.WARNING_EMPL_FIRSTNAME_MANDATORY)
    @Column(name = "FirstName",length = 20,nullable = false)
    private String firstName;

    @Length(max = 20, message = ConstantMessage.WARNING_EMPL_MAX_LENGTH_MIDDLENAME)
    @Column(name = "MiddleName",length = 20,nullable = true)
    private String middleName;

    @Length(max = 20, message = ConstantMessage.WARNING_EMPL_MAX_LENGTH_LASTNAME)
    @Column(name = "LastName",length = 20,nullable = true)
    private String lastName;

    @Length(max = 20, message = ConstantMessage.WARNING_EMPL_ADDRESS_MAX_LENGTH)
    @Column(name = "Address", nullable = true)
    private String address;
    @NotNull(message = ConstantMessage.WARNING_EMPL_BIRTHDATE_MANDATORY)
    @Column(name = "BirthDate",nullable = false)
    private LocalDate birthDate;

    @Transient
    private Integer age;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    public Integer getAge() {
        return Period.between(this.birthDate,LocalDate.now()).getYears();//dikonversi ke short dari Integer
    }
}