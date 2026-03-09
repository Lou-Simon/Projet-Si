package com.dtos;

import lombok.*;

/**
 * Data Transfer Object (DTO) utilisé pour la mise à jour des informations d'un utilisateur.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {
    private String nom;
    private String prenom;
    private Integer age;
    private String adresse;
    private String motDePasse;
}