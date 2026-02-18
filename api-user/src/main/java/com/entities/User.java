package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Entité représentant un utilisateur dans le système.
 * Cette classe est mappée sur la table "users" en base de données via Hibernate.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /**
     * Identifiant unique technique de l'utilisateur.
     * Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Pseudonyme unique de l'utilisateur.
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
     * Liste des adresses postales associées à l'utilisateur.
     * Mappée dans une table de collection secondaire par Hibernate.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_addresses", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "adresse")
    private List<String> adresses;
}