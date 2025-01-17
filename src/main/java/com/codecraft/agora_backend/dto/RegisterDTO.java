package com.codecraft.agora_backend.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String name;
    private String username;
    private String email;
    private String password;
}