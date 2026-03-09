package com.example.demo.repository;

import com.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository pour l'entité User.
 * Fournit les méthodes standard de manipulation des données
 * ainsi que des requêtes personnalisées basées sur le pseudo.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Recherche un utilisateur.
     *
     * @param pseudo Le pseudo à rechercher.
     * @return Un Optional contenant l'utilisateur s'il est trouvé, sinon un Optional vide.
     */
    Optional<User> findByPseudo(String pseudo);

    /**
     * Supprime un utilisateur en utilisant son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur à supprimer.
     */
    void deleteByPseudo(String pseudo);
}