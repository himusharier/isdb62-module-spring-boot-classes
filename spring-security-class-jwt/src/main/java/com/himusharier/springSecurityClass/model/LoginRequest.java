package com.himusharier.springSecurityClass.model;

public record LoginRequest(
        String username,
        String password
) {
}