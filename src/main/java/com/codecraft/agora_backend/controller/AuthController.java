package com.codecraft.agora_backend.controller;


import com.codecraft.agora_backend.dto.AuthResponseDTO;
import com.codecraft.agora_backend.dto.LoginDTO;
import com.codecraft.agora_backend.dto.RegisterDTO;
import com.codecraft.agora_backend.dto.UpdatePasswordDTO;
import com.codecraft.agora_backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing authentication.
 */
@Tag(name = "AuthController", description = "Controller for managing authentication")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    /**
     * Login endpoint.
     *
     * @param loginDto the login data transfer object
     * @return the authentication response data transfer object (JWT Bearer token)
     */
    @Operation(summary = "Login", description = "Authenticates a user and return a JWT Bearer token")
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

    /**
     * Register endpoint.
     *
     * @param registerDto the register data transfer object
     * @return a success message
     */
    @Operation(summary = "Register", description = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
        authService.register(registerDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    /**
     * Update password endpoint.
     *
     * @param userDetails the user details
     * @param updatePasswordDto the update password data transfer object
     * @return a success message
     */
    @Operation(summary = "Update password", description = "Update the password of the current authenticated user")
    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdatePasswordDTO updatePasswordDto) {
        authService.updatePassword(userDetails.getUsername(), updatePasswordDto);
        return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
    }
}
