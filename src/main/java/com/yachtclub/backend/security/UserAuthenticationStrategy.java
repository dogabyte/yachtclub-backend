package com.yachtclub.backend.security;

import com.yachtclub.backend.dtos.LoginResponseDTO;
import java.util.Optional;

public interface UserAuthenticationStrategy {
    Optional<LoginResponseDTO> authenticate(String username, String password);
}
