package com.services.impl;

import com.dtos.*;
import com.entities.User;
import com.mappers.UserMapper;
import com.example.demo.repository.UserRepository;
import com.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implémentation de l'interface UserService.
 * Contient la logique métier, la gestion transactionnelle et la gestion des exceptions HTTP.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return Une liste d'objets UserDto.
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Crée un nouvel utilisateur en base de données.
     *
     * @param userCreateDto Les données requises pour la création.
     * @throws ResponseStatusException Si le pseudo est déjà utilisé par un autre utilisateur (Statut 409 CONFLICT).
     * @return L'objet UserDto représentant l'utilisateur nouvellement créé.
     */
    @Override
    @Transactional
    public UserDto createUser(UserCreateDto dto) {
        if (userRepository.findByPseudo(dto.getPseudo()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Le pseudo existe déjà");
        }

        User user = userMapper.toEntity(dto);
        // TODO: Hasher le mot de passe ici avec PasswordEncoder avant la sauvegarde
        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    /**
     * Récupère un utilisateur spécifique par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur recherché.
     * @throws ResponseStatusException Si l'utilisateur n'est pas trouvé (Statut 404 NOT_FOUND).
     * @return L'objet UserDto correspondant.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByPseudo(String pseudo) {
        return userRepository.findByPseudo(pseudo)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param pseudo Le pseudo de l'utilisateur ciblé.
     * @param userUpdateDto Les nouvelles données à appliquer.
     * @throws ResponseStatusException Si l'utilisateur ciblé n'existe pas (Statut 404 NOT_FOUND).
     * @return L'objet UserDto mis à jour.
     */
    @Override
    @Transactional
    public UserDto updateUser(String pseudo, UserUpdateDto dto) {
        User user = userRepository.findByPseudo(pseudo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));

        if (dto.getNom() != null) user.setNom(dto.getNom());
        if (dto.getPrenom() != null) user.setPrenom(dto.getPrenom());
        if (dto.getAge() != null) user.setAge(dto.getAge());
        if (dto.getAdresse() != null) user.setAdresse(dto.getAdresse());
        if (dto.getMotDePasse() != null) {
            // TODO: Hasher le nouveau mot de passe
            user.setMotDePasse(dto.getMotDePasse());
        }

        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    /**
     * Supprime un utilisateur.
     *
     * @param pseudo Le pseudo de l'utilisateur à supprimer.
     * @throws ResponseStatusException Si l'utilisateur à supprimer n'existe pas (Statut 404 NOT_FOUND).
     */
    @Override
    @Transactional
    public void deleteUser(String pseudo) {
        if (userRepository.findByPseudo(pseudo).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
        }
        userRepository.deleteByPseudo(pseudo);
    }
}