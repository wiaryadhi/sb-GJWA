package com.bcafinance.gjwaspringbootjpa.repos;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 04/12/2022 - 10:30
Last Modified on 04/12/2022 - 10:30
Version 1.0
*/

import com.bcafinance.gjwaspringbootjpa.models.MovieGenres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieGenreRepo extends JpaRepository<MovieGenres,Long> {
    Optional<MovieGenres> findByType (String name);

    List<MovieGenres> findAll();

    List<MovieGenres> findByTypeLike(String name);
    List<MovieGenres> findByTypeNotLike(String name);

    List<MovieGenres> findByTypeStartsWith(String name);

    List<MovieGenres> findByTypeEndsWith(String name);
}
