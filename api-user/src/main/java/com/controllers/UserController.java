package com.controllers;

import com.dtos.*;
import com.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateDto userCreateDto) {
        UserDto createdUser = userService.createUser(userCreateDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{pseudo}")
    public ResponseEntity<UserDto> getUserByPseudo(@PathVariable String pseudo) {
        return ResponseEntity.ok(userService.getUserByPseudo(pseudo));
    }

    @PutMapping("/{pseudo}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String pseudo, @RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.updateUser(pseudo, userUpdateDto));
    }

    @DeleteMapping("/{pseudo}")
    public ResponseEntity<Void> deleteUser(@PathVariable String pseudo) {
        userService.deleteUser(pseudo);
        return ResponseEntity.ok().build();
    }
}