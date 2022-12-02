package com.bcafinance.gjwaspringbootjpa.repos;

import com.bcafinance.gjwaspringbootjpa.models.MovieDirectors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 01/12/2022 - 15:04
Last Modified on 01/12/2022 - 15:04
Version 1.0
*/

public interface MovieDirectorRepo extends JpaRepository<MovieDirectors,Long> {

    Optional<MovieDirectors> findByName (String name);

    List<MovieDirectors> findAll();

    List<MovieDirectors> findByNameLike(String name);
    List<MovieDirectors> findByNameNotLike(String name);

    List<MovieDirectors> findByNameStartsWith(String name);

    List<MovieDirectors> findByNameEndsWith(String name);

}
