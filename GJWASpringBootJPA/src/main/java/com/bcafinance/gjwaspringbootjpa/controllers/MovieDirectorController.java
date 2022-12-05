package com.bcafinance.gjwaspringbootjpa.controllers;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 01/12/2022 - 15:19
Last Modified on 01/12/2022 - 15:19
Version 1.0
*/

import com.bcafinance.gjwaspringbootjpa.dto.MovieDirectorDTO;
import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.handler.ResponseHandler;
import com.bcafinance.gjwaspringbootjpa.models.MovieDirectors;
import com.bcafinance.gjwaspringbootjpa.services.MovieDirectorService;
import com.bcafinance.gjwaspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class MovieDirectorController {

    @Getter
    private MovieDirectorService movieDirectorService;

    public MovieDirectorController() {
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public MovieDirectorController(MovieDirectorService movieDirectorService) {
        this.movieDirectorService = movieDirectorService;
    }


    @PostMapping("/v1/moviedirectors")
    public ResponseEntity<Object>
    saveMovieDirector(@RequestBody MovieDirectors movieDirectors) throws Exception {

        if (movieDirectors == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        movieDirectorService.saveMovieDirector(movieDirectors);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED, null, null, null);
    }
    @PostMapping("v1/moviedirectors/batch")
    public ResponseEntity<Object> saveAllDirector(@RequestBody List<MovieDirectors> movieDirectors) throws Exception {

        if (movieDirectors == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        movieDirectorService.saveAllDirector(movieDirectors);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED, null, null, null);
    }

    @GetMapping("/v1/moviedirectors/{id}")
    public ResponseEntity<Object> getMovieDirectorByID(@PathVariable("id") long id) throws Exception {
        MovieDirectors movieDirectors = movieDirectorService.findByIdMovieDirector(id);

        if (movieDirectors != null) {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,movieDirectors, null, null);
        } else {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }
    @GetMapping("/v1/moviedirectors/dto/{id}")
    public ResponseEntity<Object> getMovieDirectorByIDDTO(@PathVariable("id") long id) throws Exception {
        MovieDirectors movieDirectors = movieDirectorService.findByIdMovieDirector(id);

        if (movieDirectors != null) {
            MovieDirectorDTO movieDirectorDTO = modelMapper.map(movieDirectors,MovieDirectorDTO.class);
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,movieDirectorDTO, null, null);
        } else {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/moviedirectors/datas/all/0")
    public ResponseEntity<Object> findAllMovieDirectors() throws Exception {

        int data = 0;
        List<MovieDirectors> lsMovieDirectors = movieDirectorService.findAllMovieDirectors();

        if (lsMovieDirectors.size() == 0) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsMovieDirectors, null, null);
    }

    @GetMapping("/v1/moviedirectors/datas/dto/all/")
    public ResponseEntity<Object> findAllDirectorDTO() throws Exception {

        List<MovieDirectors> lsMovies = movieDirectorService.findAllMovieDirectors();

        if (lsMovies.size() == 0) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<MovieDirectorDTO> lsMovieDTO = modelMapper.map(lsMovies, new TypeToken<List<MovieDirectorDTO>>() {
        }.getType());

        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsMovieDTO, null, null);
    }

    @GetMapping("/v1/moviedirectors/datas/search/{name}")
    public ResponseEntity<Object> getMovieDirectorByName(@PathVariable("name") String name) throws Exception {

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, movieDirectorService.findByNameDirectors(name), null, null);
    }

    @PutMapping("/v1/moviedirectors/t")
    public ResponseEntity<Object> updateMovieDirectorByID(@RequestBody MovieDirectors movieDirectors) throws Exception {
        movieDirectorService.updateMovieDirectorById(movieDirectors);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, "", null, null);
    }

    @GetMapping("/v1/moviedirectors/datas/like/{name}")
    public ResponseEntity<Object> findByNameLike(@PathVariable("name") String name) throws Exception {

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, movieDirectorService.findByNameDirectorLike(name), null, null);
    }

    @GetMapping("/v1/moviedirectors/datas/like/dto/{name}")
    public ResponseEntity<Object> findByNameLikeDTO(@PathVariable("name") String name) throws Exception {

        List<MovieDirectors> lsMovieDirectors = movieDirectorService.findByNameDirectorLike(name);
        if (lsMovieDirectors.size() == 0) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<MovieDirectorDTO> lsMovieDirectorDTO = modelMapper.map(lsMovieDirectors, new TypeToken<List<MovieDirectorDTO>>() {
        }.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsMovieDirectorDTO, null, null);
    }

    @GetMapping("/v1/moviedirectors/datas/notlike/{name}")
    public ResponseEntity<Object> findNameNotLike(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,movieDirectorService.findNameNotLike(name),null,null);}

    @GetMapping("v1/moviedirectors/datas/start/{name}")
    public ResponseEntity<Object> findNameStartWith(@PathVariable("name") String name) throws Exception {
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, movieDirectorService.findByNameStartsWith(name), null, null);
    }

    @GetMapping("/v1/moviedirectors/datas/end/{name}")
    public ResponseEntity<Object> findNameEndWith(@PathVariable("name") String name)throws Exception{
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,movieDirectorService.findNameEndsWith(name),null,null);
    }
}
