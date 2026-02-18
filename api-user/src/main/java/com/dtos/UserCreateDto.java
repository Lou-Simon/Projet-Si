package com.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {
    private String pseudo;
    private String nom;
    private String prenom;
    private Integer age;
    private String adresse;
    private String motDePasse;
}