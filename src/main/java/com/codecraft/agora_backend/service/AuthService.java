package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.LoginDTO;
import com.codecraft.agora_backend.dto.RegisterDTO;
import com.codecraft.agora_backend.dto.UpdatePasswordDTO;

public interface AuthService {
    String login(LoginDTO loginDto);
    void register(RegisterDTO registerDto);
    void updatePassword(String username, UpdatePasswordDTO updatePasswordDTO);
}