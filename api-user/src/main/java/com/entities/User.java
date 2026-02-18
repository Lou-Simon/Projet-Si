package com.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entité représentant un utilisateur dans le système VOD.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String pseudo;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private Integer age;

    @Column
    private String adresse;

    @Column(nullable = false)
    private String motDePasse;
}