package com.acme.app.api.mapper;

import com.acme.app.api.dto.ProductDto;
import com.acme.app.infrastructure.jpa.*;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(ProductEntity entity) {
        Long empId = entity.getEmployee() != null ? entity.getEmployee().getId() : null;
        return new ProductDto(entity.getId(), entity.getName(), entity.getPrice(), empId);
    }

    public ProductEntity toEntity(ProductDto dto, EmployeeEntity employee) {
        return ProductEntity.builder()
                .name(dto.name())
                .price(dto.price())
                .employee(employee)
                .build();
    }
}
