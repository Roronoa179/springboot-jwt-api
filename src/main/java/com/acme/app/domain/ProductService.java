package com.acme.app.domain;

import com.acme.app.api.dto.ProductDto;
import com.acme.app.api.mapper.ProductMapper;
import com.acme.app.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductMapper mapper;

    @Transactional
    public ProductDto create(ProductDto dto) {
        EmployeeEntity employee = null;
        if (dto.employeeId() != null) {
            employee = employeeRepository.findById(dto.employeeId())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado: " + dto.employeeId()));
        }
        ProductEntity saved = productRepository.save(mapper.toEntity(dto, employee));
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
        return mapper.toDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id))
            throw new RuntimeException("Producto no encontrado: " + id);
        productRepository.deleteById(id);
    }
}
