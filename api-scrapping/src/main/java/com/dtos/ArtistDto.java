package com.dtos;

import com.services.impl.enums.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ArtistDto {
    @NotBlank(message = "Le nom est obligatoire")
    private String name;

    @NotBlank(message = "Le job est obligatoire")
    private JobType job;

    @NotNull
    private Date birthday;

    @NotBlank(message = "La biographie est obligatoire")
    private String biography;
}



