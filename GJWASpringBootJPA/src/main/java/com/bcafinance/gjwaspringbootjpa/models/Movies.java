package com.bcafinance.gjwaspringbootjpa.models;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 30/11/2022 - 14:15
Last Modified on 30/11/2022 - 14:15
Version 1.0
*/


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "Movie")
public class Movies {

    @ManyToOne
    private MovieDirectors movieDirectors;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovieId")
    private Long id;

    @Column(name = "Title",length = 50,nullable = false)
    private String title;

    @Column(name = "Budget", nullable = true)
    private long budget;

//    @NotNull(message = "jangan sampai null yee") // ini pesan kalo inputannya null
    @Column(name = "ReleaseDate",nullable = true)
    private LocalDate releaseDate;

    @Column(name = "Revenue",nullable = true)
    private long revenue;

    @Column(name = "Runtime",nullable = true)
    private int runtime;

    @Column(name = "Rating",nullable = true)
    private double rating;

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

    public Movies() {
    }

}
