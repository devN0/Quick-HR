package com.reverb.quickhr.quickhr.security.jwt;

import com.reverb.quickhr.quickhr.user.UserService;
import com.reverb.quickhr.quickhr.user.dtos.UserResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationManager implements AuthenticationManager {
    private JwtService jwtService;
    private UserService userService;

    public JwtAuthenticationManager(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // first check if Authentication object is of JwtAuthentication type
        if(authentication instanceof JwtAuthentication) {
            JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;
            // extract jwt from jwtAuthentication
            String jwtString = jwtAuthentication.getCredentials();
            // extract username from jwtString
            String username = jwtService.getUsernameFromJwt(jwtString);
            // check if user exists with username
            UserResponseDto userResponseDto = userService.getUserByUsername(username);
            jwtAuthentication.setUserResponseDto(userResponseDto);
            return jwtAuthentication;
        }
        return null;
    }
}
