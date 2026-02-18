package com.example.demo.service;

import com.example.demo.dto.UserDto;
import java.util.List;

/**
 * Interface définissant le contrat pour la gestion des utilisateurs.
 */
public interface UserService {

    /**
     * Crée un nouvel utilisateur.
     */
    UserDto createUser(UserDto userDto);

    /**
     * Récupère tous les utilisateurs.
     */
    List<UserDto> getAllUsers();

    /**
     * Récupère un utilisateur par son pseudonyme.
     */
    UserDto getUserByPseudo(String pseudo);
}