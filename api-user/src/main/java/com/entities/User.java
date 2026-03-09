package com.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entité JPA représentant un utilisateur dans le système VOD.
 * Mappée sur la table "users" de la base de données relationnelle.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /** 
     * Identifiant. 
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 
     * Pseudo de l'utilisateur. 
    */
    @Column(nullable = false, unique = true)
    private String pseudo;

    /** 
     * Nom de famille de l'utilisateur. 
    */
    @Column(nullable = false)
    private String nom;

    /** 
     * Prénom de l'utilisateur. 
    */
    @Column(nullable = false)
    private String prenom;

    /** 
     * Âge de l'utilisateur. 
    */
    @Column(nullable = false)
    private Integer age;

    /** 
     * Adresse de l'utilisateur. 
    */
    @Column
    private String adresse;

    /** 
     * Mot de passe de l'utilisateur. 
    */
    @Column(nullable = false)
    private String motDePasse;
}