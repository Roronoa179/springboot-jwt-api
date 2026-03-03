package com.acme.app.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductDto(
        Long id,

        @NotBlank(message = "El nombre es obligatorio")
        String name,

        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
        BigDecimal price,

        Long employeeId // 🔹 Nuevo campo
) {}
