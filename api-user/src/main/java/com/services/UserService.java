package com.services;

import com.dtos.*;

import java.util.List;

/**
 * Interface définissant la logique métier liée à la gestion des utilisateurs.
 */
public interface UserService {

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return Une liste d'objets UserDto.
     */
    List<UserDto> getAllUsers();
    
    /**
     * Crée un nouvel utilisateur en base de données.
     *
     * @param userCreateDto Les données requises pour la création.
     * @return L'objet UserDto représentant l'utilisateur nouvellement créé.
     */
    UserDto createUser(UserCreateDto userCreateDto);

    /**
     * Récupère un utilisateur spécifique par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur recherché.
     * @return L'objet UserDto correspondant.
     */
    UserDto getUserByPseudo(String pseudo);

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param pseudo Le pseudo de l'utilisateur ciblé.
     * @param userUpdateDto Les nouvelles données à appliquer.
     * @return L'objet UserDto mis à jour.
     */
    UserDto updateUser(String pseudo, UserUpdateDto userUpdateDto);

    /**
     * Supprime un utilisateur.
     *
     * @param pseudo Le pseudo de l'utilisateur à supprimer.
     */
    void deleteUser(String pseudo);
}