package com.bcafinance.gjwaspringbootjpa.dto;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 03/12/2022 - 14:13
Last Modified on 03/12/2022 - 14:13
Version 1.0
*/
import com.bcafinance.gjwaspringbootjpa.models.Division;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieGenreDTO {

    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_MVG_MANDATORY)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
