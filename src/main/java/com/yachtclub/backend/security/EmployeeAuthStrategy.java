package com.yachtclub.backend.security;

import com.yachtclub.backend.dtos.LoginResponseDTO;
import com.yachtclub.backend.entities.Employee;
import com.yachtclub.backend.entities.Role;
import com.yachtclub.backend.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeAuthStrategy implements UserAuthenticationStrategy {

    private final EmployeeService employeeService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<LoginResponseDTO> authenticate(String username, String password) {
        Optional<Employee> employeeOpt = employeeService.getByUsername(username);

        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (passwordEncoder.matches(password, employee.getPassword())) {
                String token = jwtTokenProvider.createToken(employee.getUsername(), Role.EMPLOYEE.name());
                return Optional.of(new LoginResponseDTO(
                        employee.getId(),
                        employee.getUsername(),
                        Role.EMPLOYEE,
                        token));
            }
        }
        return Optional.empty();
    }
}
