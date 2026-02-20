package com.controllers;

import com.dtos.MovieBatchDto;
import com.dtos.MovieDto;
import com.services.MovieBatchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*") // pour avoir accès via le client vueJS
@RestController
@RequestMapping("/movies")
public class MovieBatchController {

    private final MovieBatchService movieBatchService;

    public MovieBatchController(MovieBatchService movieBatchService) {
        this.movieBatchService = movieBatchService;
    }

    /**
     * Method to {nbmovies} the movies randmoly
     */
    @GetMapping("/batch/{nbMovies}")

    public MovieBatchDto getMovie(@PathVariable("nbMovies") int nbMovies) {
        try {
            return movieBatchService.getMovies(nbMovies);
        } catch (EntityNotFoundException e) {
            return new MovieBatchDto();
        }
    }

}