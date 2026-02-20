package com.services.impl;

import com.dtos.MovieBatchDto;
import com.dtos.MovieDto;
import com.services.MovieBatchService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class MovieBatchServiceImpl implements MovieBatchService {

    private final String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNThlYzNjYmE5MWRjMTkzZjYwYzNjMDVlYTdmNWY3NSIsIm5iZiI6MTc3MTQ0MTgwOS44NjksInN1YiI6IjY5OTYwZTkxMmE0ZGNiNWUxMDg2MzQ0YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SD6o8-oZAROPyzp8gYAMlLahDerDGW9AD_8BFQAn-_8";
        private final WebClient webClient = WebClient.builder()
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("Authorization", "Bearer " + token)
                .defaultHeader("accept", "application/json")
                .codecs(config -> config.defaultCodecs().maxInMemorySize(5 * 1024 * 1024))
                .build();

    @Override
    public MovieBatchDto getMovies(int nbMovies) {
        List<MovieDto> imported = new ArrayList<>();
        int skipped = 0;
        Random random = new Random();

        while (imported.size() < nbMovies) {
            try {
                imported.add(getMovieById(random.nextInt(1000000)));
            } catch (Exception e) {
                skipped++;
            }
        }

        MovieBatchDto result = new MovieBatchDto();
        result.setMovies(imported);
        result.setImported(imported.size());
        result.setSkipped(skipped);
        return result;
    }

        private MovieDto getMovieById(int id) {
            Map m = webClient.get()
                    .uri("/movie/{id}?append_to_response=credits&language=en-US", id)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            Map credits = (Map) m.get("credits");

            MovieDto movie = new MovieDto();
            movie.setTitle((String) m.get("title"));
            movie.setDescription((String) m.get("overview"));
            movie.setReleaseYear(Integer.parseInt(((String) m.get("release_date")).substring(0, 4)));
            movie.setRating(Float.parseFloat(m.get("vote_average").toString()));
            movie.setGenres(((List<Map>) m.get("genres")).stream().map(g -> (String) g.get("name")).toList());
            movie.setActors(((List<Map>) credits.get("cast")).stream().map(c -> (String) c.get("name")).toList());
            movie.setDirector(((List<Map>) credits.get("crew")).stream().filter(c -> "Director".equals(c.get("job"))).map(c -> (String) c.get("name")).findFirst().orElse(null));

            return movie;
        }
}