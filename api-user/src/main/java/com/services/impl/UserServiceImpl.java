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

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

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

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByPseudo(String pseudo) {
        return userRepository.findByPseudo(pseudo)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

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

    @Override
    @Transactional
    public void deleteUser(String pseudo) {
        if (userRepository.findByPseudo(pseudo).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé");
        }
        userRepository.deleteByPseudo(pseudo);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthTokenDto login(UserLoginDto loginDto) {
        User user = userRepository.findByPseudo(loginDto.getPseudo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants incorrects"));

        // TODO: Remplacer par un check via PasswordEncoder.matches()
        if (!user.getMotDePasse().equals(loginDto.getMotDePasse())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Identifiants incorrects");
        }

        // TODO: Remplacer par la génération d'un vrai JWT
        String dummyToken = UUID.randomUUID().toString();

        return AuthTokenDto.builder()
                .token(dummyToken)
                .user(userMapper.toDto(user))
                .build();
    }
}