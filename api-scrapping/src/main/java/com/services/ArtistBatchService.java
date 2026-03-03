package com.services;

import com.dtos.ArtistBatchDto;


/**
 * Interface définissant les opérations métier disponibles pour la gestion des films.
 * Cette interface suit le principe d'Interface Segregation (SOLID).
 */
public interface ArtistBatchService {

    /**
     * Récupère des  films aléatoirement
     * @return les films trouvés
     */
    ArtistBatchDto getArtists(int nbArtists);

}