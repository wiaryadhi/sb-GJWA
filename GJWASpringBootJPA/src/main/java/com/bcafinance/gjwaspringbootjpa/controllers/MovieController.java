package com.bcafinance.gjwaspringbootjpa.controllers;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 30/11/2022 - 14:25
Last Modified on 30/11/2022 - 14:25
Version 1.0
*/

import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.handler.ResponseHandler;
import com.bcafinance.gjwaspringbootjpa.models.Movies;
import com.bcafinance.gjwaspringbootjpa.services.MovieService;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class MovieController {

    @Getter
    private MovieService movieService;


    public MovieController() {
    }

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/v1/movies")
    public ResponseEntity<Object>
    saveMovie(@RequestBody Movies movies) throws Exception {

        if (movies == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        movieService.saveMovies(movies);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED, null, null, null);
    }

    @GetMapping("/v1/movies/{id}")
    public ResponseEntity<Object> getMovieByID(@PathVariable("id") long id) throws Exception {
        Movies movies = movieService.findByIdMovies(id);

        if (movies != null) {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, movies, null, null);
        } else {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/movies/datas/all/0")
    public ResponseEntity<Object> findAllMovies() throws Exception {

        int data = 0;
        List<Movies> lsMovies = movieService.findAllMovies();

        if (lsMovies.size() == 0) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsMovies, null, null);
    }

    @GetMapping("/v1/movies/datas/search/{title}")
    public ResponseEntity<Object> getMovieByTitle(@PathVariable("title") String title) throws Exception {

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, movieService.findByTitleMovies(title), null, null);
    }


    @PutMapping("/v1/movies/t")
    public ResponseEntity<Object> updateMovieByID(@RequestBody Movies movies) throws Exception {
        movieService.updateMovieById(movies);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, "", null, null);
    }

    @GetMapping("/v1/movies/datas/like/{title}")
    public ResponseEntity<Object> findByTitleLike(@PathVariable("title") String title) throws Exception {

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, movieService.findByTitleMovieLike(title), null, null);
    }


@GetMapping("v1/movies/datas/start/{title}")
public ResponseEntity<Object> findTitleStartWith(@PathVariable("title") String title) throws Exception {
    return new ResponseHandler().
            generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, movieService.findByTitleStartsWith(title), null, null);
}

    @GetMapping("/v1/movies/datas/end/{title}")
    public ResponseEntity<Object> findTitleEndWith(@PathVariable("title") String title)throws Exception{
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,movieService.findTitleEndsWith(title),null,null);
}

    @GetMapping("/v1/movies/datas/notlike/{title}")
    public ResponseEntity<Object> findTitleNotLike(@PathVariable("title") String title)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,movieService.findTitleNotLike(title),null,null);}

}
//
//    @PutMapping("/v2/customers/t")
//    public ResponseEntity<Object> updateCustomerByIDV2(@RequestBody Customers customers)throws Exception{
//        movieService.updateCustomerByIdV2(customers);
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
//    }
//
//}
//
