package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.UserDto;
import com.codecraft.agora_backend.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}