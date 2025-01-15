package com.roomfit.be.auth.application.domain;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenExtractor {

    public String extractTokenFromHeader(String authorizationHeader) throws IllegalAccessException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalAccessException("Access Denied: No token provided");
        }
        return authorizationHeader.substring(7);
    }
}