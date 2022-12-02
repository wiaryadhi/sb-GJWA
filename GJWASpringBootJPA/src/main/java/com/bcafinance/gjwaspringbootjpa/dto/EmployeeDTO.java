package com.bcafinance.gjwaspringbootjpa.dto;

import com.bcafinance.gjwaspringbootjpa.models.Division;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

//@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO {

    private Long id;

    @Length(message = ConstantMessage.WARNING_EMPL_MAX_LENGTH_EMAIL)
    @NotEmpty(message = ConstantMessage.WARNING_EMPL_EMAIL_MANDATORY)
    private String email;

    @Length(message = ConstantMessage.WARNING_EMPL_MAX_LENGTH_PHONE)
    @NotEmpty(message = ConstantMessage.WARNING_EMPL_PH_NUMBER_MANDATORY)
    private String phoneNumber;

    @Length(max = 20, message = ConstantMessage.WARNING_EMPL_MAX_LENGTH_FIRSTNAME)
    @NotEmpty(message = ConstantMessage.WARNING_EMPL_FIRSTNAME_MANDATORY)
    private String firstName;

    @Length(max = 20, message = ConstantMessage.WARNING_EMPL_MAX_LENGTH_MIDDLENAME)
    private String middleName;

    @Length(max = 20, message = ConstantMessage.WARNING_EMPL_MAX_LENGTH_LASTNAME)
    private String lastName;

    @Length(max = 20, message = ConstantMessage.WARNING_EMPL_ADDRESS_MAX_LENGTH)
    private String address;
    @NotNull(message = ConstantMessage.WARNING_EMPL_BIRTHDATE_MANDATORY)
    private LocalDate birthDate;

    private Integer age;

    private String divisionName;

    private Division division;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return Period.between(this.birthDate,LocalDate.now()).getYears();//dikonversi ke short dari Integer
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }
}
