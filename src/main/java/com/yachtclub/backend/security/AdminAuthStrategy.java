package com.yachtclub.backend.security;

import com.yachtclub.backend.dtos.LoginResponseDTO;
import com.yachtclub.backend.entities.Role;
import com.yachtclub.backend.repositories.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminAuthStrategy implements UserAuthenticationStrategy {

    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Optional<LoginResponseDTO> authenticate(String username, String password) {
        return administratorRepository.findByUsername(username)
                .filter(admin -> passwordEncoder.matches(password, admin.getPassword()))
                .map(admin -> {
                    String token = jwtTokenProvider.createToken(admin.getUsername(), Role.ADMIN.name());
                    return new LoginResponseDTO(admin.getId(), admin.getUsername(), Role.ADMIN, token);
                });
    }
}
