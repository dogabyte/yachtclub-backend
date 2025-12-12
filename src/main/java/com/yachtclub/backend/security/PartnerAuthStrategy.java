package com.yachtclub.backend.security;

import com.yachtclub.backend.dtos.LoginResponseDTO;
import com.yachtclub.backend.entities.Partner;
import com.yachtclub.backend.entities.Role;
import com.yachtclub.backend.services.interfaces.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerAuthStrategy implements UserAuthenticationStrategy {

    private final PartnerService partnerService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<LoginResponseDTO> authenticate(String username, String password) {
        Optional<Partner> partnerOpt = partnerService.getByUsername(username);

        if (partnerOpt.isPresent()) {
            Partner partner = partnerOpt.get();
            log.info("Partner found: {}, checking password", username);
            boolean passwordMatches = passwordEncoder.matches(password, partner.getPassword());
            log.info("Password matches: {}", passwordMatches);
            if (passwordMatches) {
                String token = jwtTokenProvider.createToken(partner.getUsername(), Role.PARTNER.name());
                return Optional.of(new LoginResponseDTO(
                        partner.getId(),
                        partner.getUsername(),
                        Role.PARTNER,
                        token));
            }
        } else {
            log.info("Partner not found: {}", username);
        }
        return Optional.empty();
    }
}
