package com.dtos;

import lombok.*;

/**
 * Data Transfer Object (DTO) utilisé pour exposer les données d'un utilisateur vers le client.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String pseudo;
    private String nom;
    private String prenom;
    private Integer age;
    private String adresse;
}