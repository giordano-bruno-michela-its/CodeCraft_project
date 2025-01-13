package com.codecraft.agora_backend.service;

import com.codecraft.agora_backend.dto.LoginDto;
import com.codecraft.agora_backend.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    void register(RegisterDto registerDto);
}