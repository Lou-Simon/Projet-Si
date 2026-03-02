package com.services.impl;

import com.dtos.ArtistDto;
import com.services.impl.enums.JobType;
import com.services.ArtistService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNThlYzNjYmE5MWRjMTkzZjYwYzNjMDVlYTdmNWY3NSIsIm5iZiI6MTc3MTQ0MTgwOS44NjksInN1YiI6IjY5OTYwZTkxMmE0ZGNiNWUxMDg2MzQ0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SD6o8-oZAROPyzp8gYAMlLahDerDGW9AD_8BFQAn-_8";

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.themoviedb.org/3")
            .defaultHeader("Authorization", "Bearer " + token)
            .defaultHeader("accept", "application/json")
            .build();


    /**
     * Méthode qui permet de récupérer un artiste par son nom
     * @param name le nom de l'artiste
     * @return un ArtistDto
     */
    @Override
    public ArtistDto getArtistByName(String name) {
        // 1er appel : recherche de l'artiste par nom pour obtenir l'id
        Map response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/person")
                        .queryParam("query", name)
                        .queryParam("language", "en-US")
                        .queryParam("page", 1)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        //Si l'acteur est introuvable, on retourne une exception
        if (response == null || !response.containsKey("results")) throw new EntityNotFoundException("Artiste non trouvés");

        List<Map> results = (List<Map>) response.get("results");
        if (results.isEmpty()) throw new EntityNotFoundException("Artiste non trouvé");

        Map artistInfos = results.get(0);
        Integer personId = (Integer) artistInfos.get("id");

        // 2ème appel : détails complet via /person/{id}
        Map personDetails = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/person/{id}")
                        .queryParam("language", "en-US")
                        .build(personId))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (personDetails == null) throw new EntityNotFoundException("Détails de l'artiste non trouvés");

        ArtistDto artist = new ArtistDto();

        artist.setName((String) personDetails.get("name"));

        artist.setBiography((String) personDetails.get("biography"));

        String department = (String) personDetails.get("known_for_department");

        switch(department) {
            case "Acting" -> artist.setJob(JobType.Actor);
            case "Directing" -> artist.setJob(JobType.Director);
            default -> artist.setJob(null);
        }


        String birthdayStr = (String) personDetails.get("birthday");
        if (birthdayStr != null) {
            try {
                artist.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr));
            } catch (ParseException e) {
                artist.setBirthday(null);
            }
        }

        return artist;
    }
}
