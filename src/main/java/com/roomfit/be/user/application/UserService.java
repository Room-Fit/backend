package com.roomfit.be.user.application;

import com.roomfit.be.user.application.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO.Response create(UserDTO.Create request);
    UserDTO.Response readById(Long id);
    List<UserDTO.Response> readAll();
}
