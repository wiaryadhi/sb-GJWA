package com.bcafinance.gjwaspringbootjpa.models;


import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
@Table(name = "MstDivision")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DivisionID")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_DIVISION_NAME_MANDATORY)
    @Column(name = "DivisionName",length = 50,nullable = false)
    private String name;

    @NotEmpty(message = ConstantMessage.WARNING_DIVISION_DESC_MANDATORY)
    @Column(name = "DivisionDescription",length = 255,nullable = false)
    private String description;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;
}