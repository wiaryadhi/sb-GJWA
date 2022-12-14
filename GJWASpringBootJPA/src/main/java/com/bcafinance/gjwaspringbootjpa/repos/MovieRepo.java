package com.bcafinance.gjwaspringbootjpa.repos;

import com.bcafinance.gjwaspringbootjpa.models.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MovieRepo extends JpaRepository<Movies,Long> {

    Optional<Movies> findByTitle(String title);

    Optional<Movies> findByRating(double rating);


    @Override
    List<Movies> findAll();

//        @Query("SELECT Title FROM Movies m WHERE m.title LIKE ?1%")
    List<Movies> findByTitleLike(String title);

    List<Movies> findByTitleNotLike(String title);

    List<Movies> findByTitleStartsWith(String title);

    List<Movies> findByTitleEndsWith(String title);



}
