package com.yachtclub.backend.controllers;

import com.yachtclub.backend.dtos.LoginRequestDTO;
import com.yachtclub.backend.dtos.LoginResponseDTO;
import com.yachtclub.backend.services.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ConfigurationProperties
@RestController
@RequestMapping("${api.endpoint.auth}")
@lombok.extern.slf4j.Slf4j
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @jakarta.validation.Valid @RequestBody LoginRequestDTO loginRequest

    ) {
        log.info("body received: user={}", loginRequest.getUsername());
        LoginResponseDTO user = authService.login(
                loginRequest.getUsername(),
                loginRequest.getPassword());
        if (user != null) {
            log.info("user found: {}", user.getUsername());
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
