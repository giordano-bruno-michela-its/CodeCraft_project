package com.codecraft.agora_backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CodeEmailRequestDTO {
    private String email;

    private String code;
}
