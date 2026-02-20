package com.services.impl;

import com.dtos.MovieDto;
import com.services.MovieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {

    private final String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNThlYzNjYmE5MWRjMTkzZjYwYzNjMDVlYTdmNWY3NSIsIm5iZiI6MTc3MTQ0MTgwOS44NjksInN1YiI6IjY5OTYwZTkxMmE0ZGNiNWUxMDg2MzQ0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SD6o8-oZAROPyzp8gYAMlLahDerDGW9AD_8BFQAn-_8";

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.themoviedb.org/3")
            .defaultHeader("Authorization", "Bearer "+token)
            .defaultHeader("accept", "application/json")
            .codecs(config -> config.defaultCodecs().maxInMemorySize(5 * 1024 * 1024)) //Les répopnses de certaines requêtes sont trop longues, il faut augmenter le buffer.
            .build();

    @Override
    public MovieDto getMovieByTitle(String title) {
        // appel 1 : recherche par titre pour récupérer l'id et avoir plus d'informations
        Map response = webClient.get()
                .uri("/search/movie?query={title}&language=en-US&page=1", title)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map> results = (List<Map>) response.get("results");
        if (results == null || results.isEmpty()) throw new EntityNotFoundException("Film non trouvé");

        int id = (int) results.get(0).get("id");

        // Appel 2 : recherche du film par l'id avec toutes ses informations
        Map m = webClient.get()
                .uri("/movie/{id}?language=en-US", id)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // Appel 3 : ici, on récupère les crédits pour avoir la lsite des acteurs
        Map credits = webClient.get()
                .uri("/movie/{id}/credits?language=en-US", id)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<String> actors = ((List<Map>) credits.get("cast"))
                .stream()
                .map(c -> (String) c.get("name"))
                .toList();

        String director = ((List<Map>) credits.get("crew"))
                .stream()
                .filter(c -> "Director".equals(c.get("job")))
                .map(c -> (String) c.get("name"))
                .findFirst().orElse(null);

        List<String> genres = ((List<Map>) m.get("genres"))
                .stream()
                .map(g -> (String) g.get("name"))
                .toList();

        MovieDto movie = new MovieDto();
        movie.setTitle((String) m.get("title"));
        movie.setDescription((String) m.get("overview"));
        movie.setReleaseYear(Integer.parseInt(((String) m.get("release_date")).substring(0, 4)));
        movie.setRating(Float.parseFloat(m.get("vote_average").toString()));
        movie.setGenres(genres);
        movie.setDirector(director);
        movie.setActors(actors);

        return movie;
    }
}
