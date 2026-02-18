package com.example.demo.dto;

import lombok.*;

import java.util.List;

/**
 * Objet de Transfert de Données (DTO) pour l'entité User.
 * Utilisé pour exposer les données via l'API REST sans exposer directement le modèle de base de données.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    /**
     * Pseudonyme de l'utilisateur.
     */
    private String pseudo;

    /**
     * Nom de l'utilisateur.
     */
    private String nom;

    /**
     * Prénom de l'utilisateur.
     */
    private String prenom;

    /**
     * Âge de l'utilisateur.
     */
    private Integer age;

    /**
     * Liste des adresses de l'utilisateur.
     */
    private List<String> adresses;
}