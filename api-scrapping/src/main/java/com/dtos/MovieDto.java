package com.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @NotNull
    private Integer releaseYear;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @NotBlank(message = "Le réalisateur est obligatoire")
    private String director;

    @NotBlank(message = "Les acteurs sont obligatoires")
    private List<String> actors;

    @NotBlank(message = "Les genres sont obligatoires")
    private List<String> genres;

    @NotNull
    private int minAge;

    @NotNull
    private Float rating;
}



