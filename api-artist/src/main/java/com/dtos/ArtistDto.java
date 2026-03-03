package com.dtos; 

import lombok.*;
import java.time.LocalDate;

/**
 * Data Transfer Object représentant un artiste.
 *
 * <p>Ce DTO est utilisé pour exposer les données
 * via l'API REST sans exposer directement l'entité JPA.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {

	/**
	 * Identifiant de l'artiste.
	 */
	private Long id;

	/**
	 * Nom de l'artiste.
	 */
	private String name;

	/**
	 * Genre artistique.
	 */
	private String genre;

	/**
	 * Pays d'origine.
	 */
	private String country;

	/**
	 * Date de naissance.
	 */
	private LocalDate birthDate;

	/**
	 * Biographie.
	 */
	private String biography;
}
