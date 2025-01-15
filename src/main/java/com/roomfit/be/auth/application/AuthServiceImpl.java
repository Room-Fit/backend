package com.roomfit.be.auth.application;

import com.roomfit.be.auth.domain.JwtTokenProvider;
import com.roomfit.be.auth.application.dto.AuthDTO;
import com.roomfit.be.auth.application.dto.UserDetails;
import com.roomfit.be.email.EmailSendEvent;
import com.roomfit.be.global.util.RandomCodeGenerator;
import com.roomfit.be.redis.application.ReactiveRedisService;
import com.roomfit.be.user.application.UserService;
import com.roomfit.be.user.application.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ReactiveRedisService reactiveRedisService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RandomCodeGenerator codeGenerator;
    private final UserService userService;
    @Override
    public AuthDTO.LoginResponse authenticate(AuthDTO.Login request) {
        UserDTO.Response foundUser = userService.readByEmail(request.getEmail());
        if(!request.getPassword().equals(foundUser.getPassword()))
             throw new RuntimeException("Not found");

        UserDetails userDetails = UserDetails.of(foundUser.getId(), foundUser.getEmail(), foundUser.getNickname(), foundUser.getRole());

        String accessToken = jwtTokenProvider.generateAccessToken(userDetails);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails);

        reactiveRedisService.saveWithTTL(request.getEmail(), refreshToken, 300)
                .subscribe();

        return AuthDTO.LoginResponse.of(accessToken, refreshToken);
    }

    /**
     * TODO: 이 작업을 별도의 서비스나 작업 큐로 오프로드하여 AuthServiceImpl 클래스의 부하를 줄이는 방법도 고려할 수 있습니다.
     */
    @Async("taskExecutor")
    @Override
    public void generateVerificationCode(String authToken, String email) {

        String code = codeGenerator.generateSecureRandomCode(5);
        reactiveRedisService.saveWithTTL(authToken, code, 300)
                .doOnTerminate(() -> {
                    String subject = "Verification Code";
                    String body = "Here is your verification code: " + code;
                    applicationEventPublisher.publishEvent(new EmailSendEvent(email, subject, body));
                })
                .subscribe();
    }

    @Override
    public boolean verifyVerificationCode(String authToken, String code) {
        /**
         * 동기적으로
         */
        String savedCode = (String) reactiveRedisService.get(authToken).block(); // block()을 사용하여 동기적으로 값 가져오기
        reactiveRedisService.saveWithTTL(authToken, true, 300)
                .subscribe();
        if (savedCode != null && savedCode.equals(code)) {
            saveVerificationStatus(authToken, "success");
            return true;
        } else {
            saveVerificationStatus(authToken, "failure");
            return false;
        }
    }

    @Async("taskExecutor")
    public void saveVerificationStatus(String authToken, String status) {
        // 인증 상태를 Redis에 동기적으로 저장
        reactiveRedisService.saveWithTTL(authToken, status, 1000).subscribe();  // block()을 사용하여 동기적으로 저장
    }
}
