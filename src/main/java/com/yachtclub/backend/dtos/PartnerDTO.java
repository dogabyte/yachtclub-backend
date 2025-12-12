package com.yachtclub.backend.dtos;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PartnerDTO {
    private Long id;
    @jakarta.validation.constraints.NotBlank(message = "Name is required")
    private String name;

    @jakarta.validation.constraints.NotBlank(message = "Last name is required")
    private String lastName;

    @jakarta.validation.constraints.NotBlank(message = "Address is required")
    private String address;

    @jakarta.validation.constraints.NotBlank(message = "DNI is required")
    private String dni;

    @jakarta.validation.constraints.NotBlank(message = "Phone is required")
    private String phone;

    private LocalDate registrationDate;

    @jakarta.validation.constraints.NotBlank(message = "Username is required")
    private String username;

    @jakarta.validation.constraints.NotBlank(message = "Password is required")
    @jakarta.validation.constraints.Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String role;
    private List<MooringDTO> moorings;
    private List<BoatDTO> boats;

}
