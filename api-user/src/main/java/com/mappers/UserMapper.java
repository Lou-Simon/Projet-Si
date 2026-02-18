package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

/**
 * Composant responsable de la conversion entre l'entité {@link User}
 * et son DTO correspondant {@link UserDto}.
 */
@Component
public class UserMapper {

    /**
     * Convertit une entité User en UserDto.
     *
     * @param user L'entité utilisateur à convertir.
     * @return Le DTO correspondant, ou null si l'entrée est null.
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
                .adresses(user.getAdresses())
                .build();
    }

    /**
     * Convertit un UserDto en entité User.
     * Note: L'ID technique n'est pas mappé ici car il est généré par la BDD.
     *
     * @param userDto Le DTO utilisateur à convertir.
     * @return L'entité correspondante, ou null si l'entrée est null.
     */
    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return User.builder()
                .pseudo(userDto.getPseudo())
                .nom(userDto.getNom())
                .prenom(userDto.getPrenom())
                .age(userDto.getAge())
                .adresses(userDto.getAdresses())
                .build();
    }
}