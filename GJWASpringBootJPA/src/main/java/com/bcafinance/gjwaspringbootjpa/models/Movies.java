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


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Movie")
public class Movies implements Serializable {
    private static final long serialversionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovieId")
    private Long id;

    @ManyToOne
    private MovieDirectors movieDirectors;

    @ManyToMany
    @JoinTable(name = "MovieMovieGenre", joinColumns = @JoinColumn(name = "MovieId", referencedColumnName = "MovieId"), inverseJoinColumns = @JoinColumn(name = "GenreId", referencedColumnName = "GenreId"))
//    @JsonManagedReference
    private Set<MovieGenres> movieGenres = new HashSet<MovieGenres>();

    @Column(name = "Title", length = 50, nullable = false)
    private String title;

    @Column(name = "Budget", nullable = true)
    private long budget;

    //    @NotNull(message = "jangan sampai null yee") // ini pesan kalo inputannya null
    @Column(name = "ReleaseDate", nullable = true)
    private LocalDate releaseDate;

    @Column(name = "Revenue", nullable = true)
    private long revenue;

    @Column(name = "Runtime", nullable = true)
    private int runtime;

    @Column(name = "Rating", nullable = true)
    private double rating;

    @Column(name = "CreatedBy", nullable = false)
    private String createdBy = "Arya";

    @Column(name = "CreatedDate", nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy", nullable = true)
    private String modifiedBy;

    @Column(name = "ModifiedDate", nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieDirectors getMovieDirectors() {
        return movieDirectors;
    }

    public void setMovieDirectors(MovieDirectors movieDirectors) {
        this.movieDirectors = movieDirectors;
    }

    public Set<MovieGenres> getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(Set<MovieGenres> movieGenres) {
        this.movieGenres = movieGenres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
