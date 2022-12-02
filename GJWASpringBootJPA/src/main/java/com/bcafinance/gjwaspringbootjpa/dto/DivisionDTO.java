package com.bcafinance.gjwaspringbootjpa.dto;

import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DivisionDTO {

    private Long id;

    @Email(message = ConstantMessage.ERROR_EMAIL_FORMAT_INVALID)
    @Length(max = 50 , message = ConstantMessage.WARNING_DIVISION_NAME_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_DIVISION_NAME_MANDATORY)
    private String name;

    @NotEmpty(message = ConstantMessage.WARNING_DIVISION_DESC_MANDATORY)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}