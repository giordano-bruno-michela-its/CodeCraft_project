package com.codecraft.agora_backend.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private boolean deleted;
}