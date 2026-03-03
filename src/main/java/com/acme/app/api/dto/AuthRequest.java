package com.acme.app.api.dto;

public record AuthRequest(
        String username,
        String password
) {}