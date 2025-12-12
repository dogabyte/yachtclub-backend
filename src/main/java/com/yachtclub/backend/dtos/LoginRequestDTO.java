package com.yachtclub.backend.dtos;

import lombok.Data;

@Data
public class LoginRequestDTO {
    @jakarta.validation.constraints.NotBlank(message = "Username is required")
    private String username;

    @jakarta.validation.constraints.NotBlank(message = "Password is required")
    private String password;
}
