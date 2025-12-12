package com.yachtclub.backend.services.impl;

import com.yachtclub.backend.dtos.LoginResponseDTO;
import com.yachtclub.backend.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final java.util.List<com.yachtclub.backend.security.UserAuthenticationStrategy> authStrategies;

    @Override
    public LoginResponseDTO login(String username, String password) {
        return authStrategies.stream()
                .map(strategy -> strategy.authenticate(username, password))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }
}
