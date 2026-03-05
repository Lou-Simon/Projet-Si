package com.controllers;

import com.dtos.ArtistBatchDto;
import com.services.ArtistBatchService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/artists")
public class ArtistBatchController {

    private final ArtistBatchService artistBatchService;

    public ArtistBatchController(ArtistBatchService artistBatchService) {
        this.artistBatchService = artistBatchService;
    }

    /**
     * Méthode pour récupérer {nbArtists} artistes de manière aléatoire (uniquement les acteurs/trices et Réalisateurs/trices)
     * @param nbArtists le nombre d'artistes à récupérer
     * @retrun un ArtistBatchDto
     */
    @GetMapping("/batch/{nbArtists}")

    public ArtistBatchDto getArtists(@PathVariable("nbArtists") int nbArtists) {
        try {
            return artistBatchService.getArtists(nbArtists);
        } catch (EntityNotFoundException e) {
            return new ArtistBatchDto();
        }
    }

}