package com.codecraft.agora_backend.controller;


import com.codecraft.agora_backend.dto.AuthResponseDTO;
import com.codecraft.agora_backend.dto.LoginDTO;
import com.codecraft.agora_backend.dto.RegisterDTO;
import com.codecraft.agora_backend.dto.UpdatePasswordDTO;
import com.codecraft.agora_backend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto){

        //01 - Receive the token from AuthService
        String token = authService.login(loginDto);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        AuthResponseDTO authResponseDto = new AuthResponseDTO();
        authResponseDto.setAccessToken(token);

        //03 - Return the response to the user
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
        authService.register(registerDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdatePasswordDTO updatePasswordDto) {
        authService.updatePassword(userDetails.getUsername(), updatePasswordDto);
        return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
    }
}
