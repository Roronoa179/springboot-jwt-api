package com.acme.app.domain;

import com.acme.app.infrastructure.jpa.UserEntity;
import com.acme.app.infrastructure.jpa.UserRepository;
import com.acme.app.infrastructure.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder; // 👈 se asegura la inyección real del bean global

    @Autowired
    private JwtService jwtService;

    public String register(String username, String password) {
        if (repository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        UserEntity user = UserEntity.builder()
                .username(username)
                .password(encoder.encode(password))  // usa el mismo encoder global
                .role("USER")
                .build();

        repository.save(user);

        log.info("✅ Usuario registrado: {}", username);
        return jwtService.generateToken(username);
    }


    public String login(String username, String password) {
        UserEntity user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        log.info("Encoder inyectado: {}", encoder.getClass().getName());
        log.info("HASH: {}", user.getPassword());
        log.info("MATCH: {}", encoder.matches(password, user.getPassword()));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtService.generateToken(username);
    }
}
