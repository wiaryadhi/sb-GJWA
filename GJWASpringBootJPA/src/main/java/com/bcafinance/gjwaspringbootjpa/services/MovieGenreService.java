package com.bcafinance.gjwaspringbootjpa.services;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 04/12/2022 - 10:28
Last Modified on 04/12/2022 - 10:28
Version 1.0
*/
import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.MovieGenres;
import com.bcafinance.gjwaspringbootjpa.models.Movies;
import com.bcafinance.gjwaspringbootjpa.repos.MovieGenreRepo;
import com.bcafinance.gjwaspringbootjpa.repos.MovieRepo;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@Transactional
public class MovieGenreService {
    private MovieGenreRepo movieGenreRepo;

    @Autowired
    public MovieGenreService(MovieGenreRepo movieGenreRepo) {
        this.movieGenreRepo = movieGenreRepo;
    }

    public List<MovieGenres> findAllType() {
        return movieGenreRepo.findAll();
    }

    public MovieGenres findByIdGenres(Long id) throws Exception {
        return movieGenreRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public MovieGenres findByTypeGenre(String type) throws Exception {

        return movieGenreRepo.findByType(type).orElseThrow(() ->
                new ResourceNotFoundException(ConstantMessage.WARNING_MOVIE_NOT_FOUND));
    }

    public List<MovieGenres> findByTypeLike(String type) throws Exception {

        return movieGenreRepo.findByTypeLike(type);
    }

    public List<MovieGenres> findByTypeStartsWith(String type) throws Exception{
        return movieGenreRepo.findByTypeStartsWith(type);
    }
    public List<MovieGenres> findTypeEndsWith(String type)
    {
        return movieGenreRepo.findByTypeEndsWith(type);
    }

    public List<MovieGenres> findTypeNotLike(String type){
        return movieGenreRepo.findByTypeNotLike(type);
    }

    public void saveMovieGenre(MovieGenres movieGenres) throws Exception {
        if (movieGenres.getType() == null) throw new DataIntegrityViolationException(ConstantMessage.ERROR_MOVIE_INVALID);

        Optional<MovieGenres> movieByGenre = movieGenreRepo.findByType(movieGenres.getType());

        movieGenreRepo.save(movieGenres);
    }

    @Transactional
    public void updateMoviegenreById(MovieGenres m) throws Exception{

        MovieGenres movieGenres = movieGenreRepo.findById(m.getId()).orElseThrow(() ->
                new ResourceNotFoundException(ConstantMessage.WARNING_MOVIE_NOT_FOUND));

        movieGenres.setModifiedBy("1");
        movieGenres.setModifiedDate(new Date());
        if (m.getType() != null
                && !Objects.equals(movieGenres.getType(), m.getType()) //Membandingkan string dari api dan dari database
                && !m.getType().equals("")) {
            movieGenres.setType(m.getType());//BERARTI ADA PERUBAHAN DI SINI
        }

    }

    }
