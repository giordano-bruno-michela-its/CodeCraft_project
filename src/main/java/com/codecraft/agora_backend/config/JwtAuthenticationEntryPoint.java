package com.codecraft.agora_backend.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Component that handles unauthorized access attempts by sending an HTTP 401 Unauthorized error response.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    /**
     * Commences an authentication scheme.
     * <p>
     * This method is called when an unauthenticated user tries to access a secured resource.
     * It sends an HTTP 401 Unauthorized error response.
     *
     * @param request       the HTTP request
     * @param response      the HTTP response
     * @param authException the authentication exception
     * @throws IOException      if an input or output error occurs
     * @throws ServletException if a servlet error occurs
     */
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

    }
}
