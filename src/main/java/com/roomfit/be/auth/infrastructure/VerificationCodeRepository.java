package com.roomfit.be.auth.infrastructure;

import com.roomfit.be.auth.domain.VerificationCode;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface VerificationCodeRepository {

    Optional<VerificationCode> findByUUid(String uuid);
    Mono<VerificationCode> save(VerificationCode verificationCode);
    Mono<VerificationCode> update(VerificationCode verificationCode);
    Optional<Boolean> findStatusByAuthToken(String authToken);
}
