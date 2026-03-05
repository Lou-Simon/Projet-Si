package com.controllers;

import com.dtos.MovieDto;
import com.services.MovieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Méthode pour récupérer le film par son titre
     * @param  name le titre du film
     * @return un MovieDto
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