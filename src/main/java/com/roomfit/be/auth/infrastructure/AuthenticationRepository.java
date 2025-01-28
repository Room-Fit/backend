package com.roomfit.be.auth.infrastructure;

import com.roomfit.be.auth.domain.AuthToken;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticationRepository extends CrudRepository<AuthToken, String> {
}
