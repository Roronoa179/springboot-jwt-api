package com.acme.app.domain;

import com.acme.app.infrastructure.jpa.UserEntity;
import com.acme.app.infrastructure.jpa.UserRepository;
import com.acme.app.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @Transactional
    public String register(String username, String password) {
        if (repository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        UserEntity user = UserEntity.builder()
                .username(username)
                .password(encoder.encode(password))
                .role("USER")
                .build();

        repository.save(user);

        return jwtService.generateToken(username);
    }
}
