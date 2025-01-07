package com.roomfit.be.user;

import java.util.List;

public interface UserService {
    UserDTO.Response create(UserDTO.Create request);
    UserDTO.Response readById(Long id);
    List<UserDTO.Response> readAll();
}
