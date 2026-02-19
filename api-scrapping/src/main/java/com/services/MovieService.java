package com.services;

import com.dtos.MovieDto;

import java.util.List;

/**
 * Interface définissant les opérations métier disponibles pour la gestion des films.
 * Cette interface suit le principe d'Interface Segregation (SOLID).
 */
public interface MovieService {

    /**
     * Récupère un film par son titre
     * @param movieTitle l'identifiant du chien recherché
     * @return le film trouvé
     * @throws jakarta.persistence.EntityNotFoundException si le film n'existe pas
     */
    MovieDto getMovieByTitle(String movieTitle);

}