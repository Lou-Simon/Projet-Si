package com.controllers;

import com.dtos.MovieBatchDto;
import com.dtos.MovieDto;
import com.services.MovieBatchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*") // pour y avoir accès via le client vueJS
@RestController
@RequestMapping("/movies")
public class MovieBatchController {

    private final MovieBatchService movieBatchService;

    public MovieBatchController(MovieBatchService movieBatchService) {
        this.movieBatchService = movieBatchService;
    }

    /**
     * Méthode pour récupérer {nbMovies} films de manière aléatoire
     * @param nbMovies le nombre de films à récupérer
     * return un MovieBatchDto
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