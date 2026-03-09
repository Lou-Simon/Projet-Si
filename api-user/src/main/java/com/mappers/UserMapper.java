package com.mappers;

import com.dtos.UserCreateDto;
import com.dtos.UserDto;
import com.entities.User;
import org.springframework.stereotype.Component;

/**
 * Composant responsable de la conversion entre les entités JPA User
 * et les différents DTO associés.
 */
@Component
public class UserMapper {

    /**
     * Convertit une entité User en UserDto.
     *
     * @param user L'entité User.
     * @return Un objet UserDto, ou null.
     */
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .pseudo(user.getPseudo())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .age(user.getAge())
                .adresse(user.getAdresse())
                .build();
    }

    /**
     * Convertit un UserCreateDto en entité User.
     *
     * @param dto L'objet UserCreateDto contenant les données de la requête.
     * @return L'entité User.
     */
    public User toEntity(UserCreateDto dto) {
        if (dto == null) {
            return null;
        }
        return User.builder()
                .pseudo(dto.getPseudo())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .age(dto.getAge())
                .adresse(dto.getAdresse())
                .motDePasse(dto.getMotDePasse())
                .build();
    }
}