package com.mappers; // Corrigé pour correspondre à ton dossier

// Imports corrigés vers tes nouveaux emplacements
import com.entities.Artist;
import com.dtos.ArtistDto;

/**
 * Mapper responsable de la conversion entre Artist et ArtistDto.
 */
public class ArtistMapper {

    /**
     * Convertit une entité Artist en ArtistDto.
     */
    public static ArtistDto toDTO(Artist artist) {
        if (artist == null) return null;

        return ArtistDto.builder()
                .id(artist.getId())
                .name(artist.getName())
                .genre(artist.getGenre())
                .country(artist.getCountry())
                .birthDate(artist.getBirthDate())
                .biography(artist.getBiography())
                .build();
    }

    /**
     * Convertit un ArtistDto en entité Artist.
     */
    public static Artist toEntity(ArtistDto dto) {
        if (dto == null) return null;

        return Artist.builder()
                .id(dto.getId())
                .name(dto.getName())
                .genre(dto.getGenre())
                .country(dto.getCountry())
                .birthDate(dto.getBirthDate())
                .biography(dto.getBiography())
                .build();
    }
}