package com.yachtclub.backend.services.interfaces;

import com.yachtclub.backend.dtos.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(String username, String password);
    void logout();
}
