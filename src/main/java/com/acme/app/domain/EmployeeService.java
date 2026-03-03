package com.acme.app.domain;

import com.acme.app.api.dto.EmployeeDto;
import com.acme.app.api.mapper.EmployeeMapper;
import com.acme.app.infrastructure.jpa.EmployeeEntity;
import com.acme.app.infrastructure.jpa.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Transactional
    public EmployeeDto create(EmployeeDto dto) {
        if (repository.existsByEmail(dto.email()))
            throw new RuntimeException("Ya existe un empleado con el correo: " + dto.email());
        EmployeeEntity saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public EmployeeDto findById(Long id) {
        EmployeeEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado: " + id));
        return mapper.toDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Empleado no encontrado: " + id);
        repository.deleteById(id);
    }
}
