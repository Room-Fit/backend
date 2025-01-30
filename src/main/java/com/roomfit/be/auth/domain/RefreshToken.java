package com.roomfit.be.auth.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "refresh_token")
public class RefreshToken {
    @Id
    private String email;

    @Indexed
    private String token;
    @Builder.Default
    @TimeToLive
    private Long expiration = 180L; // (sec)

    public static RefreshToken of(String email, String token){
        return RefreshToken.builder()
                .email(email)
                .token(token)
                .build();
    }
}

