package com.roomfit.be.user.application;

import com.roomfit.be.auth.application.AuthService;
import com.roomfit.be.user.application.dto.UserDTO;
import com.roomfit.be.user.application.exception.UserEmailVerificationRequiredException;
import com.roomfit.be.user.application.exception.UserNotFoundException;
import com.roomfit.be.user.domain.User;
import com.roomfit.be.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AuthService authService;
    /**
     * TODO: AlreadyExist 추가 필요
     */
    @Transactional
    @Override
    public UserDTO.Response create(UserDTO.Create request) {
        /**
         * 해당 조건 나중에 resolver 로 처리
         */
        if(!authService.checkEmailVerificationStatus(request.getAuthToken())) {
            throw new UserEmailVerificationRequiredException();
        }

        User newUser = User.createUser(request.getNickname(), request.getEmail(), request.getPassword(), request.getBirth(), request.getStudentId(), request.getCollege(), request.getGender());
        User savedUser = userRepository.save(newUser);

        return UserDTO.Response.of(savedUser);
    }

    @Override
    public UserDTO.Response readById(Long id) {
        User foundUser =  userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return UserDTO.Response.of(foundUser);
    }

    @Override
    public List<UserDTO.Response> readAll() {
        List<User> foundUser = userRepository.findAll();

        return foundUser.stream()
                .map(UserDTO.Response::of)
                .toList();
    }
}
