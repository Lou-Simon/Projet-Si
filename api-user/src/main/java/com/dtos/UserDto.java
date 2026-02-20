package com.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String pseudo;
    private String nom;
    private String prenom;
    private Integer age;
    private String adresse;
}