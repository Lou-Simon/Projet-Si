package com.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthTokenDto {
    private String token;
    private UserDto user;
}