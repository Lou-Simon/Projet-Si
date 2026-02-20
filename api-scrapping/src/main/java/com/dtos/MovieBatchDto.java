package com.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class MovieBatchDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotNull
    private Integer imported;

    @NotNull
    private Integer skipped;

   @NotNull
    private List<MovieDto> movies;
}



