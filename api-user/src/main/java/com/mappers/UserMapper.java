package com.mappers;

import com.dtos.UserCreateDto;
import com.dtos.UserDto;
import com.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

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