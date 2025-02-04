package com.roomfit.be.auth.domain.jwt;

import com.roomfit.be.auth.application.dto.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
//TODO: 날짜 고정 CLock 기반으로 변경
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final JwtTokenParser jwtTokenParser;

    // Generates Access Token
    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, jwtProperties.getAccessTokenExpirationTime());
    }

    // Generates Refresh Token
    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails, jwtProperties.getRefreshTokenExpirationTime());
    }

    // Common method for generating tokens
    private String generateToken(UserDetails userDetails, long expirationTime) {
        return Jwts.builder()
                .setSubject(userDetails.getEmail())
                .claim("id", userDetails.getId())
                .claim("nickname", userDetails.getNickname())
                .claim("role", userDetails.getUserRole())
                .claim("surveyStage", userDetails.getSurveyStage())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public UserDetails extractUserDetailFromToken(String token) {
        Claims claims = jwtTokenParser.getClaimsFromToken(token,jwtProperties.getSecretKey());

        return UserDetails.builder()
                .id(claims.get("id", Long.class))
                .email(claims.getSubject())
                .nickname(claims.get("nickname", String.class))
                .userRole(claims.get("role", String.class))
                .surveyStage(claims.get("surveyStage", String.class))
                .build();
    }
}
