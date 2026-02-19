package com.services;

import com.dtos.ArtistDto;

import java.util.List;

/**
 * Interface définissant les opérations métier disponibles pour la gestion des artistes.
 * Cette interface suit le principe d'Interface Segregation (SOLID).
 */
public interface ArtistService {

    /**
     * Récupère un film par son titre
     * @param artistName l'identifiant de l'artiste recherché
     * @return le film trouvé
     */
    ArtistDto getArtistByName(String artistName);

}