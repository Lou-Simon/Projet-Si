package com.services;

import com.dtos.MovieBatchDto;


/**
 * Interface définissant les opérations métier disponibles pour la gestion des films.
 * Cette interface suit le principe d'Interface Segregation (SOLID).
 */
public interface MovieBatchService {

    /**
     * Récupère des  films aléatoirement
     * @return les films trouvés
     */
    MovieBatchDto getMovies(int nbMovies);

}