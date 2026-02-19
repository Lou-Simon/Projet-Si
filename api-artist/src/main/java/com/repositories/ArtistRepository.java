package com.repositories; // Doit correspondre à ton dossier /com/repositories/

import com.entities.Artist; // L'import corrigé qui pointe vers ton entité
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository JPA permettant l'accès aux données des artistes.
 *
 * <p>Hérité de {@link JpaRepository}, il fournit :
 * CRUD complet + pagination + tri.</p>
 */
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    /**
     * Recherche des artistes par genre.
     *
     * @param genre genre artistique
     * @return liste des artistes correspondants
     */
    List<Artist> findByGenre(String genre);

    /**
     * Recherche des artistes par pays.
     *
     * @param country pays d'origine
     * @return liste des artistes correspondants
     */
    List<Artist> findByCountry(String country);
}
