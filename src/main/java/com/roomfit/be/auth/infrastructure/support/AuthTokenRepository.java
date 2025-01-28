 package com.roomfit.be.auth.infrastructure.support;

import com.roomfit.be.auth.domain.AuthToken;

import java.util.Optional;

public interface AuthTokenRepository {
    Optional<AuthToken> findByEmail(String email);

    void save(AuthToken authToken);

    void delete(String email);
}
