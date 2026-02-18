package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST exposant les points de terminaison pour la gestion des utilisateurs.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Point de terminaison pour créer un utilisateur.
     *
     * @param userDto Le corps de la requête contenant les informations du nouvel utilisateur.
     * @return Une {@link ResponseEntity} contenant le DTO de l'utilisateur créé et le statut HTTP 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Point de terminaison pour récupérer tous les utilisateurs.
     *
     * @return Une {@link ResponseEntity} contenant la liste des utilisateurs et le statut HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Point de terminaison pour récupérer un utilisateur spécifique via son pseudonyme.
     *
     * @param pseudo Le pseudonyme passé dans le chemin de l'URL.
     * @return Une {@link ResponseEntity} contenant l'utilisateur trouvé et le statut HTTP 200 (OK).
     */
    @GetMapping("/{pseudo}")
    public ResponseEntity<UserDto> getUserByPseudo(@PathVariable String pseudo) {
        UserDto user = userService.getUserByPseudo(pseudo);
        return ResponseEntity.ok(user);
    }
}