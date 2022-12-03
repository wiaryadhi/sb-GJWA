package com.bcafinance.gjwaspringbootjpa.services;


import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.models.Movies;
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
public class MovieService {

    private MovieRepo movieRepo;

    @Autowired
    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public List<Movies> findAllMovies() {

        return movieRepo.findAll();
    }

    public Movies findByIdMovies(Long id) throws Exception {
        return movieRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public Movies findByTitleMovies(String title) throws Exception {

        return movieRepo.findByTitle(title).orElseThrow(() ->
                new ResourceNotFoundException(ConstantMessage.WARNING_MOVIE_NOT_FOUND));
    }

    public List<Movies> findByTitleMovieLike(String title) throws Exception {

        return movieRepo.findByTitleLike(title);
    }

    public List<Movies> findByTitleStartsWith(String title) throws Exception{
        return movieRepo.findByTitleStartsWith(title);
    }
    public List<Movies> findTitleEndsWith(String title)
    {
        return movieRepo.findByTitleEndsWith(title);
    }

    public List<Movies> findTitleNotLike(String title)
    {
        return movieRepo.findByTitleNotLike(title);
    }


    public void saveMovies(Movies movies) throws Exception {
        if (movies.getTitle() == null) throw new DataIntegrityViolationException(ConstantMessage.ERROR_MOVIE_INVALID);
        if (movies.getReleaseDate() == null)
            throw new DataIntegrityViolationException(ConstantMessage.ERROR_MOVIE_INVALID);

        // error dibawah karena data integrity exception inputnya adalah String, sedangkan budget adalah long
//        if(movies.getBudget()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//        if(movies.getRevenue()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//        if(movies.getRating()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);


        Optional<Movies> moviesByTitle = movieRepo.findByTitle(movies.getTitle());

        movieRepo.save(movies);
    }


    @Transactional
    public void updateMovieById(Movies m) throws Exception {

        Movies movies = movieRepo.findById(m.getId()).orElseThrow(() ->
                new ResourceNotFoundException(ConstantMessage.WARNING_MOVIE_NOT_FOUND));

        movies.setModifiedBy("1");
        movies.setModifiedDate(new Date());
        if (m.getTitle() != null
                && !Objects.equals(movies.getTitle(), m.getTitle()) //Membandingkan string dari api dan dari database
                && !m.getTitle().equals("")) {
            movies.setTitle(m.getTitle());//BERARTI ADA PERUBAHAN DI SINI
        }

        if (m.getBudget() != 0
                && m.getBudget() == movies.getBudget()) {
            movies.setBudget(m.getBudget()); //BERARTI ADA PERUBAHAN DI SINI
        }

        if (m.getReleaseDate() != null &&
                !m.getReleaseDate().toString().equals("") &&
                !Objects.equals(movies.getReleaseDate().toString(), m.getReleaseDate().toString())) {

            movies.setReleaseDate(m.getReleaseDate());
        }

        if (m.getRevenue() != 0
                && m.getRevenue() == movies.getRevenue()) {
            movies.setRevenue(m.getRevenue()); //BERARTI ADA PERUBAHAN DI SINI
        }

        if (m.getRating() != 0 &&
                m.getRating() != movies.getRating()) {


            if (m.getRating()>10) //validasi Rating tidak boleh lebih dari rating 10
           {
               throw new ResourceNotFoundException(ConstantMessage.WARNING_RATING_INVALID);
          }
            movies.setRating(m.getRating());
        }
        if (

                m.getRuntime() != movies.getRuntime()) {
            if (m.getRuntime()<1) //validation runtime movie harus lebih dari 1 menit
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_RUNTIME_INVALID);
            }
            movies.setRuntime(m.getRuntime());
        }
    }

    // Service create Batch
    @Transactional(rollbackFor = {Exception.class})
    public void saveAllMovies(List<Movies> ls){
        movieRepo.saveAll(ls);
    }


}