package com.acme.app.api.mapper;

import com.acme.app.api.dto.EmployeeDto;
import com.acme.app.infrastructure.jpa.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDto toDto(EmployeeEntity entity) {
        return new EmployeeDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail()
        );
    }

    public EmployeeEntity toEntity(EmployeeDto dto) {
        return EmployeeEntity.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .build();
    }
}
