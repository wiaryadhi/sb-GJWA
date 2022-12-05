package com.bcafinance.gjwaspringbootjpa.dto;

import com.bcafinance.gjwaspringbootjpa.models.MovieDirectors;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.ManyToOne;
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
Created on 02/12/2022 - 10:27
Last Modified on 02/12/2022 - 10:27
Version 1.0
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDTO {

//    private String name;
    @ManyToOne
    private MovieDirectors movieDirectors;

    private Long id;

    @Length(max = 50 , message = ConstantMessage.WARNING_DIVISION_NAME_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_DIVISION_NAME_MANDATORY)
    private String title;

    private LocalDate releaseDate;

    private long revenue;

    private int runtime;

    private double rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public MovieDirectors getMovieDirectors() {
        return movieDirectors;
    }

    public void setMovieDirectors(MovieDirectors movieDirectors) {
        this.movieDirectors = movieDirectors;
    }

    //    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
