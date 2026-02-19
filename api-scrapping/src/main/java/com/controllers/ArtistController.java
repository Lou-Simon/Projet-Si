package com.controllers;

import com.dtos.ArtistDto;
import com.services.ArtistService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    /**
     * Method to get the movie based on the title
     */
    @GetMapping("/{name}")
    public ArtistDto getArtist(@PathVariable("name") String name) {
        try {
            return artistService.getArtistByName(name);
        } catch (EntityNotFoundException e) {
            return new ArtistDto();
        }
    }

}