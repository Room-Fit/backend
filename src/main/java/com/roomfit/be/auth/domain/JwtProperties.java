package com.roomfit.be.auth.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    private String secretKey;  // 비밀 키
    private long accessTokenExpirationTime;  // Access Token 유효 기간
    private long refreshTokenExpirationTime;
}