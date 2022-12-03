package com.bcafinance.gjwaspringbootjpa.dto;

import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 03/12/2022 - 13:34
Last Modified on 03/12/2022 - 13:34
Version 1.0
*/
@JsonIgnoreProperties(ignoreUnknown = true)

public class MovieDirectorDTO {
    private Long id;

    @Length(max = 30, message = ConstantMessage.WARNING_MVD_MAX_LENGTH_NAME)
    @NotEmpty(message = ConstantMessage.WARNING_MVD_NAME_MANDATORY)
    private String name;

    private LocalDate birthdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
