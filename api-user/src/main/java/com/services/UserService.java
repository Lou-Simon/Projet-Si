package com.services;

import com.dtos.*;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto createUser(UserCreateDto userCreateDto);
    UserDto getUserByPseudo(String pseudo);
    UserDto updateUser(String pseudo, UserUpdateDto userUpdateDto);
    void deleteUser(String pseudo);
}