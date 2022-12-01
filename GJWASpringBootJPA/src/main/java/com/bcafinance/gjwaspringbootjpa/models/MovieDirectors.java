package com.bcafinance.gjwaspringbootjpa.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 01/12/2022 - 14:53
Last Modified on 01/12/2022 - 14:53
Version 1.0
*/
@Data
@Entity
@Table(name = "MovieDirector")
public class MovieDirectors {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DirectorId")
    private Long id;

    @Column(name = "Name",length = 50,nullable = false)
    private String name;

    @Column(name = "Birthdate",nullable = true)
    private Date birthdate;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "Arya";

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    public MovieDirectors() {
    }
}
