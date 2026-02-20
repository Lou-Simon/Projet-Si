package com.services; // On change l'adresse ici !

import com.dtos.ArtistDto; // On importe le DTO depuis son nouvel emplacement
import java.util.List;

/**
 * Service métier responsable de la gestion des artistes.
 */
public interface ArtistService {

    ArtistDto create(ArtistDto dto);

    ArtistDto getById(Long id);

    List<ArtistDto> getAll();

    ArtistDto update(Long id, ArtistDto dto);

    void delete(Long id);
}