package com.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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

    @NotNull
    private Float rating;
}



