package com.services.impl;

import com.dtos.ArtistBatchDto;
import com.dtos.ArtistDto;
import com.services.ArtistBatchService;
import com.services.impl.enums.JobType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ArtistBatchServiceImpl implements ArtistBatchService {
    private final String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNThlYzNjYmE5MWRjMTkzZjYwYzNjMDVlYTdmNWY3NSIsIm5iZiI6MTc3MTQ0MTgwOS44NjksInN1YiI6IjY5OTYwZTkxMmE0ZGNiNWUxMDg2MzQ0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SD6o8-oZAROPyzp8gYAMlLahDerDGW9AD_8BFQAn-_8";
    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.themoviedb.org/3")
            .defaultHeader("Authorization", "Bearer " + token)
            .defaultHeader("accept", "application/json")
            .codecs(config -> config.defaultCodecs().maxInMemorySize(5 * 1024 * 1024))
            .build();

    /**
     * Méthode qui permet de récupérer une liste d'artistes
     * @param nbArtists le nombre d'artistes à récupérer
     * @return un ArtistBatchDto
     */
    @Override
    public ArtistBatchDto getArtists(int nbArtists) {
        List<ArtistDto> imported = new ArrayList<>();
        int skipped = 0;
        Random random = new Random();
        // permet de récupérer nbArtists films non nulls, sinon on les ignore (l'API peut renvoyer des artistes nulls)

        while (imported.size() < nbArtists) {
            try {
                imported.add(getArtistById(random.nextInt(1000))); // permet de générer un identifiant d'artiste compris entre 0 et 1000'
            } catch (Exception e) {
                skipped++;
            }
        }

        ArtistBatchDto result = new ArtistBatchDto();
        result.setArtists(imported);
        result.setImported(imported.size());
        result.setSkipped(skipped);
        return result;
    }

    /**
     * Méthode qui permet de récupérer un artiste par son idendifiant
     * @param id l'identifiant de l'artiste
     * @return un ArtistDto
     */
    private ArtistDto getArtistById(int id) {
        Map m = webClient.get()
                .uri("/person/{id}?append_to_response=movie_credits&language=en-US", id)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        String department = (String) m.get("known_for_department");

        // permet de récupérer uniquement des acteurs et/ou directeurs. si ce n'est pas le cas, on ignore l'artiste et skipped est incrémenté
        if (!"Acting".equalsIgnoreCase(department) && !"Directing".equalsIgnoreCase(department)) {
            throw new RuntimeException("Not an actor or director, skipping");
        }

        JobType job = "Directing".equalsIgnoreCase(department) ? JobType.Director : JobType.Actor;

        Date birthday = null;
        String birthdayStr = (String) m.get("birthday");
        if (birthdayStr != null && !birthdayStr.isEmpty()) {
            try {
                birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);
            } catch (Exception e) {}
        }

        ArtistDto artist = new ArtistDto();
        artist.setName((String) m.get("name"));
        artist.setBiography((String) m.get("biography"));
        artist.setJob(job);
        artist.setBirthday(birthday);

        return artist;
    }

}