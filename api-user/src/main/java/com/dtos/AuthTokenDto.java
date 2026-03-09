package com.dtos;

import lombok.*;

/**
 * Data Transfer Object (DTO) utilisé pour encapsuler un jeton d'authentification
 * ainsi que les informations de l'utilisateur authentifié.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthTokenDto {
    private String token;
    private UserDto user;
}