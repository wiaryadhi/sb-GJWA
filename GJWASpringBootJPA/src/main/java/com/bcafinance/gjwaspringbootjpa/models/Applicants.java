package com.bcafinance.gjwaspringbootjpa.models;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 05/12/2022 - 11:06
Last Modified on 05/12/2022 - 11:06
Version 1.0
*/

import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
@Table(name="Applicant")
public class Applicants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppId")
    private Long id;

    @Column(name = "Title",nullable = false)
    private String title;
    @NotEmpty(message = ConstantMessage.WARNING_APP_FIRSTNAME_MANDATORY)
    @Column(name = "FirstName",nullable = false)
    private String firstName;

    @NotEmpty(message = ConstantMessage.WARNING_APP_LASTNAME_MANDATORY)
    @Column(name = "LastName",nullable = false)
    private String lastname;

    @NotEmpty(message = ConstantMessage.WARNING_APP_AVATAR_MANDATORY)
    @Column(name = "Avatar",nullable = false)
    private String avatar;

    @NotEmpty(message = ConstantMessage.WARNING_APP_LANGUAGE_MANDATORY)
    @Column(name = "Language",nullable = false)
    private String language;

    @NotEmpty(message = ConstantMessage.WARNING_APP_SKILL_MANDATORY)
    @Column(name = "Skill",nullable = false)
    private String skill;

    @NotEmpty(message = ConstantMessage.WARNING_APP_UNIVERSITY_MANDATORY)
    @Column(name = "University",nullable = false)
    private String university;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "Arya";

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    public Applicants() {
    }


}
