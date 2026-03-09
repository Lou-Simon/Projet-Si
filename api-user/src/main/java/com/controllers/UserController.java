package com.controllers;

import com.dtos.*;
import com.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST gérant les opérations CRUD pour les utilisateurs.
 * Expose les points de terminaison sous le chemin de base "/user".
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Récupère la liste de tous les utilisateurs enregistrés.
     *
     * @return Une ResponseEntity contenant la liste des objets UserDto.
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Crée un nouvel utilisateur.
     *
     * @param userCreateDto Les données de l'utilisateur à créer.
     * @return Une ResponseEntity contenant le UserDto créé.
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateDto userCreateDto) {
        UserDto createdUser = userService.createUser(userCreateDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Récupère les détails d'un utilisateur spécifique.
     *
     * @param pseudo Le pseudo de l'utilisateur recherché.
     * @return Une ResponseEntity contenant le UserDto trouvé.
     */
    @GetMapping("/{pseudo}")
    public ResponseEntity<UserDto> getUserByPseudo(@PathVariable String pseudo) {
        return ResponseEntity.ok(userService.getUserByPseudo(pseudo));
    }

    /**
     * Met à jour les informations d'un utilisateur.
     *
     * @param pseudo Le pseudo de l'utilisateur.
     * @param userUpdateDto Les nouvelles données de l'utilisateur.
     * @return Une ResponseEntity contenant le UserDto mis à jour.
     */
    @PutMapping("/{pseudo}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String pseudo, @RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.updateUser(pseudo, userUpdateDto));
    }

    /**
     * Supprime un utilisateur.
     *
     * @param pseudo Le pseudo de l'utilisateur à supprimer.
     * @return Une ResponseEntity vide.
     */
    @DeleteMapping("/{pseudo}")
    public ResponseEntity<Void> deleteUser(@PathVariable String pseudo) {
        userService.deleteUser(pseudo);
        return ResponseEntity.ok().build();
    }
}