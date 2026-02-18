package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Dépôt de données pour l'entité {@link User}.
 * Fournit les opérations CRUD de base via Spring Data JPA.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Recherche un utilisateur par son pseudonyme.
     *
     * @param pseudo Le pseudonyme exact à rechercher.
     * @return Un {@link Optional} contenant l'utilisateur s'il est trouvé, sinon vide.
     */
    Optional<User> findByPseudo(String pseudo);
}