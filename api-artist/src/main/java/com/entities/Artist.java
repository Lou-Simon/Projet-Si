package com.entities; // Garde uniquement le package qui correspond à ton dossier réel

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entité représentant un artiste musical ou cinématographique.
 *
 * <p>Un artiste possède :</p>
 * <ul>
 *     <li>Un identifiant unique généré automatiquement</li>
 *     <li>Un nom</li>
 *     <li>Un genre (musical ou cinématographique)</li>
 *     <li>Un pays d'origine</li>
 *     <li>Une date de naissance</li>
 *     <li>Une biographie descriptive</li>
 * </ul>
 *
 * <p>Cette entité est persistée via JPA/Hibernate.</p>
 */
@Entity
@Table(name = "artists")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

	/**
	 * Identifiant unique de l'artiste.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nom complet ou nom de scène de l'artiste.
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * Genre artistique (ex: Pop, Rap, Cinema, Rock, Drama...).
	 */
	@Column(nullable = false)
	private String genre;

	/**
	 * Pays d'origine de l'artiste.
	 */
	@Column(nullable = false)
	private String country;

	/**
	 * Date de naissance de l'artiste.
	 */
	@Column(name = "birth_date")
	private LocalDate birthDate;

	/**
	 * Biographie détaillée de l'artiste.
	 */
	@Column(columnDefinition = "TEXT")
	private String biography;
}
