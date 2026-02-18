package com.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDto {
    private String pseudo;
    private String motDePasse;
}