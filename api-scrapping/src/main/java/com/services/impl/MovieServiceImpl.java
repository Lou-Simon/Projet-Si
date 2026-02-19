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
            .defaultHeader("Authorization", "Bearer " + token)
            .defaultHeader("accept", "application/json")
            .build();

    @Override
    public MovieDto getMovieByTitle(String title) {
        Map response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("query", title)
                        .queryParam("language", "en-US")
                        .queryParam("page", 1)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response == null || !response.containsKey("results")) return null;

        List<Map> results = (List<Map>) response.get("results");
        if (results.isEmpty()) throw new EntityNotFoundException("Film no trouvé");


        Map movieInfos = results.get(0);

        MovieDto movie = new MovieDto();
        movie.setTitle((String) movieInfos.get("title"));
        movie.setDescription((String) movieInfos.get("overview"));

        String releaseDate = (String) movieInfos.get("release_date");
        movie.setReleaseYear(Integer.parseInt(releaseDate.substring(0, 4)));


        Object voteAverage = movieInfos.get("vote_average");
        Float rate = Float.parseFloat(voteAverage.toString());
        movie.setRating(rate);


        return movie;
    }
}
