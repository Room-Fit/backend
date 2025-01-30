 package com.roomfit.be.auth.infrastructure;

import com.roomfit.be.auth.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> findByEmail(String email);

    void save(RefreshToken refreshToken);

    void delete(String email);
}
