package com.controllers;

import com.dtos.MovieDto;
import com.services.MovieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*") // pour avoir accès via le client vueJS
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Method to get the movie based on the title
     */
    @GetMapping("/{name}")
    public MovieDto getMovie(@PathVariable("name") String name) {
        try {
            return movieService.getMovieByTitle(name);
        } catch (EntityNotFoundException e) {
            return new MovieDto();
        }
    }

}