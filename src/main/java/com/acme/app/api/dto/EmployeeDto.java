package com.acme.app.api.dto;

import jakarta.validation.constraints.*;

public record EmployeeDto(
        Long id,
        @NotBlank(message = "El nombre es obligatorio") String firstName,
        @NotBlank(message = "El apellido es obligatorio") String lastName,
        @Email(message = "El correo no es válido") String email
) {}
