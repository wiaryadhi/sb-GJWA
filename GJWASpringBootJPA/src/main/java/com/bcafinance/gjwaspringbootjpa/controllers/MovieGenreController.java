package com.bcafinance.gjwaspringbootjpa.controllers;
/*
@Author wiary a.k.a. Arya
ITDP 7
---------------------------------------
| Created with:IntelliJ IDEA          |
| Version: 2022.2.3 (Ultimate Edition)|
| Build id: #IU-222.4345.14           |
---------------------------------------
Created on 04/12/2022 - 11:45
Last Modified on 04/12/2022 - 11:45
Version 1.0
*/

import com.bcafinance.gjwaspringbootjpa.dto.MovieDTO;
import com.bcafinance.gjwaspringbootjpa.dto.MovieDirectorDTO;
import com.bcafinance.gjwaspringbootjpa.dto.MovieGenreDTO;
import com.bcafinance.gjwaspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.gjwaspringbootjpa.handler.ResponseHandler;
import com.bcafinance.gjwaspringbootjpa.models.MovieGenres;
import com.bcafinance.gjwaspringbootjpa.models.Movies;
import com.bcafinance.gjwaspringbootjpa.services.MovieGenreService;
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
public class MovieGenreController {

    @Getter
    private MovieGenreService movieGenreService;

    @Autowired
    private ModelMapper modelMapper;

    public MovieGenreController() {

    }

    @Autowired
    public MovieGenreController(MovieGenreService movieGenreService) {
        this.movieGenreService = movieGenreService;
    }

    // find all with DTO
    @GetMapping("/v1/movieGenre/dto/all")
    public ResponseEntity<Object> findAllGenreDTO() throws Exception {

        List<MovieGenres> lsMovieG = movieGenreService.findAllType();

        if (lsMovieG.size() == 0) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<MovieGenreDTO> lsMovieGDTO = modelMapper.map(lsMovieG, new TypeToken<List<MovieGenreDTO>>() {
        }.getType());

        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsMovieGDTO, null, null);
    }

    // Find by ID
    @GetMapping("/v1/movieGenres/{id}")
    public ResponseEntity<Object> getGenreByID(@PathVariable("id") long id) throws Exception {
        MovieGenres movieGenres = movieGenreService.findByIdGenres(id);

        if (movieGenres != null) {
            return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, movieGenres, null, null);
        } else {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    //Find by Type
    @GetMapping("/v1/movieGenres/type/{type}")
    public ResponseEntity<Object> getMovieByTitle(@PathVariable("type") String type) throws Exception {

        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, movieGenreService.findByTypeGenre(type), null, null);
    }

    @GetMapping("/v1/movieGenres/datas/like/dto/{type}")
    public ResponseEntity<Object> findByTitleLikeDTO(@PathVariable("type") String type) throws Exception {
        List<MovieGenres> lsMovieG = movieGenreService.findByTypeLike(type);

//        int data = 0;
        if (lsMovieG.size() == 0) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<MovieGenreDTO> lsMovieDTO = modelMapper.map(lsMovieG, new TypeToken<List<MovieGenreDTO>>() {
        }.getType());

        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsMovieDTO, null, null);
    }

    @GetMapping("/v1/moviegenres/dto/{id}")
    public ResponseEntity<Object> getMovieByIDDTO(@PathVariable("id") long id) throws Exception {
        MovieGenres movieGenres = movieGenreService.findByIdGenres(id);

        if (movieGenres != null) {
            MovieGenreDTO movieDTO = modelMapper.map(movieGenres,MovieGenreDTO.class);
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,movieDTO, null, null);
        } else {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    //Post single data
    @PostMapping("/v1/movieGenre")
    public ResponseEntity<Object> saveMovieGenre(@RequestBody MovieGenres movieGenres) throws Exception {

        if (movieGenres == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        movieGenreService.saveMovieGenre(movieGenres);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED, null, null, null);
    }

    //Post bulk data
    @PostMapping("v1/movieGenre/batch")
    public ResponseEntity<Object> saveAllGenre(@RequestBody List<MovieGenres> movieGenres) throws Exception {

        if (movieGenres == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        movieGenreService.saveAllGenre(movieGenres);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED, null, null, null);
    }

    //Update data
    @PutMapping("/v1/movieGenres/update")
    public ResponseEntity<Object> updateGenreByID(@RequestBody MovieGenres movieGenres) throws Exception {
        movieGenreService.updateMoviegenreById(movieGenres);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, "", null, null);
    }

}
