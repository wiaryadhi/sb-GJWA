package com.bcafinance.gjwaspringbootjpa.services;

import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.MovieDirectors;
import com.bcafinance.gjwaspringbootjpa.repos.MovieDirectorRepo;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 01/12/2022 - 15:02
Last Modified on 01/12/2022 - 15:02
Version 1.0
*/
@Service
@Transactional
public class MovieDirectorService {
    private MovieDirectorRepo movieDirectorRepo;

    @Autowired
    public MovieDirectorService(MovieDirectorRepo movieDirectorRepo) {
        this.movieDirectorRepo = movieDirectorRepo;
    }

    public List<MovieDirectors> findAllMovieDirectors() {

        return movieDirectorRepo.findAll();
    }

    public void saveMovieDirector(MovieDirectors movieDirectors) {
        movieDirectorRepo.save(movieDirectors);
    }

    public MovieDirectors findByIdMovieDirector(Long id) throws Exception {
        return movieDirectorRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public MovieDirectors findByNameDirectors(String name) throws Exception {

        return movieDirectorRepo.findByName(name).orElseThrow(() ->
                new ResourceNotFoundException(ConstantMessage.WARNING_CUSTOMER_NOT_FOUND));
    }

    public List<MovieDirectors> findByNameDirectorLike(String name) throws Exception {
        return movieDirectorRepo.findByNameLike(name);
    }

    public List<MovieDirectors> findNameNotLike(String name)
    {
        return movieDirectorRepo.findByNameNotLike(name);
    }

    public List<MovieDirectors> findByNameStartsWith(String name) throws Exception{
        return movieDirectorRepo.findByNameStartsWith(name);
    }
    public List<MovieDirectors> findNameEndsWith(String name)
    {
        return movieDirectorRepo.findByNameEndsWith(name);
    }

    public void updateMovieDirectorById(MovieDirectors md) throws Exception {

        MovieDirectors movieDirectors = movieDirectorRepo.findById(md.getId()).orElseThrow(() ->
                new ResourceNotFoundException(ConstantMessage.WARNING_MOVIE_NOT_FOUND));

        movieDirectors.setModifiedBy("1");
        movieDirectors.setModifiedDate(new Date());
        if (md.getName() != null
                && !Objects.equals(movieDirectors.getName(), md.getName()) //Membandingkan string dari api dan dari database
                && !md.getName().equals("")) {
            movieDirectors.setName(md.getName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if (md.getBirthdate() != null &&
                !md.getBirthdate().toString().equals("") &&
                !Objects.equals(movieDirectors.getBirthdate().toString(), md.getBirthdate().toString())) {
            movieDirectors.setBirthdate(md.getBirthdate());
        }

    }

}
