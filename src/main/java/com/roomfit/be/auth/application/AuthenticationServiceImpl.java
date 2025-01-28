package com.roomfit.be.auth.application;

import com.roomfit.be.auth.application.dto.AuthDTO;
import com.roomfit.be.auth.application.dto.UserDetails;
import com.roomfit.be.auth.application.exception.InvalidIdOrPasswordException;
import com.roomfit.be.auth.application.exception.TokenGenerationFailureException;
import com.roomfit.be.auth.domain.AuthToken;
import com.roomfit.be.auth.domain.TokenType;
import com.roomfit.be.auth.domain.jwt.JwtTokenProvider;
import com.roomfit.be.auth.infrastructure.AuthenticationRepository;
import com.roomfit.be.user.application.exception.UserNotFoundException;
import com.roomfit.be.user.domain.User;
import com.roomfit.be.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationRepository authenticationRepository;

    @Override
    public AuthDTO.LoginResponse authenticate(AuthDTO.Login request) {
        User foundUser = findUserByEmailAndValidatePassword(request.getEmail(), request.getPassword());

        UserDetails userDetails = UserDetails.of(
                foundUser.getId(),
                foundUser.getEmail(),
                foundUser.getNickname(),
                foundUser.getRole().name(),
                foundUser.getStage().name()
        );
        String accessToken = generateToken(userDetails, TokenType.ACCESS);
        String refreshToken = generateToken(userDetails, TokenType.REFRESH);

        saveRefreshToken(request.getEmail(), refreshToken);

        log.info("User {} authenticated successfully.", foundUser.getEmail());
        return AuthDTO.LoginResponse.of(accessToken, refreshToken);
    }

//    @Override
//    public AuthDTO.LoginResponse reissueRefreshToken(String email,String refreshToken){
////       Iterable<AuthToken> authToken = authenticationRepository.findAll();
////       authToken.forEach(authToken1 -> {
////           log.info(authToken1.getToken());
////       });
//       return null;
////        assert authToken.isPresent();
////       log.info(String.valueOf(authToken.get()));
////       String storedRefreshToken = authToken.getToken();
////       log.info(email, refreshToken );
////       if(!storedRefreshToken.equals(refreshToken)){
////           throw new RuntimeException();
////       }
////        User foundUser = findUserByEmail(email);
////        UserDetails userDetails = UserDetails.of(
////                foundUser.getId(),
////                foundUser.getEmail(),
////                foundUser.getNickname(),
////                foundUser.getRole().name()
////        );
////        log.info(userDetails.toString());
////
////       String newRefreshToken = generateToken(userDetails,TokenType.REFRESH);
////
////       saveRefreshToken(email, newRefreshToken);
////
////       String accessToken = generateToken(userDetails, TokenType.ACCESS);
////       return AuthDTO.LoginResponse.of(accessToken,refreshToken);
////        return null;
//    }

    private User findUserByEmailAndValidatePassword(String email, String inputPassword) {
        User foundUser = findUserByEmail(email);
        validatePassword(inputPassword, foundUser.getPassword());
        return foundUser;
    }
    private User findUserByEmail(String email) {
        try {
            return userRepository.findByEmail(email)
                    .orElseThrow(UserNotFoundException::new);
        } catch (UserNotFoundException e) {
            log.error("User with email {} not found.", email);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error occurred while finding user with email {}.", email, e);
            throw new UserNotFoundException();
        }
    }

    private void validatePassword(String inputPassword, String storedPassword) {
        if (!inputPassword.equals(storedPassword)) {
            throw new InvalidIdOrPasswordException();
        }
    }


    private String generateToken(UserDetails userDetails, TokenType tokenType) {
        try {
            return switch (tokenType) {
                case ACCESS -> jwtTokenProvider.generateAccessToken(userDetails);
                case REFRESH -> jwtTokenProvider.generateRefreshToken(userDetails);
            };
        } catch (Exception e) {
            log.error("Token generation failed for user {}", userDetails.getEmail(), e);
            throw new TokenGenerationFailureException();
        }
    }

    private void saveRefreshToken(String email, String refreshToken) {
        try {
            AuthToken authToken = AuthToken.of(email, refreshToken);
            authenticationRepository.save(authToken);
            log.info("Refresh token saved for user {}", email);
        } catch (DataAccessException e) {
            log.error("Error occurred while saving refresh token for user {}", email, e);
            throw new TokenGenerationFailureException();
        }
    }
}
