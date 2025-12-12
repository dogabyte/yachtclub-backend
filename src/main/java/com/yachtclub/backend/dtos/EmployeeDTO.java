package com.yachtclub.backend.dtos;

import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    @jakarta.validation.constraints.NotBlank(message = "Code is required")
    private String code;

    @jakarta.validation.constraints.NotBlank(message = "Name is required")
    private String name;

    @jakarta.validation.constraints.NotBlank(message = "Address is required")
    private String address;

    @jakarta.validation.constraints.NotBlank(message = "Phone is required")
    private String phone;

    @jakarta.validation.constraints.NotBlank(message = "Specialty is required")
    private String specialty;

    @jakarta.validation.constraints.NotBlank(message = "Username is required")
    private String username;

    @jakarta.validation.constraints.NotBlank(message = "Password is required")
    @jakarta.validation.constraints.Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String role;
    private List<EmployeeZoneDTO> assignedZones;

}
