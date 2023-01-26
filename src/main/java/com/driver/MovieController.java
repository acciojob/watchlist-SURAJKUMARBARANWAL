package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String response=movieService.addMovie(movie);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String response=movieService.addDirector(director);
        return  new ResponseEntity(response,HttpStatus.CREATED);
    }
    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName,@RequestParam("directorName") String directorName){
        String response=movieService.addMovieDirectorPair(movieName,directorName);
        return  new ResponseEntity(response,HttpStatus.CREATED);
    }
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName){
        Movie response=movieService.getMovieByName(movieName);
        if(Objects.equals(null,response)){
            return new ResponseEntity("Movie not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response,HttpStatus.FOUND);
    }
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        Director response=movieService.getDirectorByName(name);
        if(Objects.equals(null,response)){
            return  new ResponseEntity("Director not found",HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity(response,HttpStatus.FOUND);
    }
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String name){
        List<String> response=new ArrayList<>();
        response=movieService.getMoviesByDirectorName(name);
        return  new ResponseEntity(response,HttpStatus.ACCEPTED);
    }
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> response=new ArrayList<>();
        response=movieService.findAllMovies();
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("directorName") String name){
        String response=movieService.deleteDirectorByName(name);
        if(Objects.equals("Invalid Request",response)){
            return new ResponseEntity("Invalid Request",HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity(response,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String response=movieService.deleteAllDirectors();
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }

}
