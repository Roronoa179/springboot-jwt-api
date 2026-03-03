package com.acme.app.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    // Método adicional: valida si ya existe un empleado con ese correo
    boolean existsByEmail(String email);
}
