package com.yachtclub.backend.security;

import com.yachtclub.backend.dtos.LoginResponseDTO;
import com.yachtclub.backend.entities.Administrator;
import com.yachtclub.backend.entities.Role;
import com.yachtclub.backend.services.interfaces.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdministratorAuthStrategy implements UserAuthenticationStrategy {

    private final AdministratorService administratorService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<LoginResponseDTO> authenticate(String username, String password) {
        Optional<Administrator> adminOpt = administratorService.getByUsername(username);

        if (adminOpt.isPresent()) {
            Administrator admin = adminOpt.get();
            if (passwordEncoder.matches(password, admin.getPassword())) {
                String token = jwtTokenProvider.createToken(admin.getUsername(), Role.ADMIN.name());
                return Optional.of(new LoginResponseDTO(
                        admin.getId(),
                        admin.getUsername(),
                        Role.ADMIN,
                        token));
            }
        }
        return Optional.empty();
    }
}
