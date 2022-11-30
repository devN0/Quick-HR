package com.reverb.quickhr.quickhr.security.jwt;

import com.reverb.quickhr.quickhr.user.dtos.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthentication implements Authentication {

    private String jwtString;
    private UserResponseDto userResponseDto;

    public JwtAuthentication(String jwtString) {
        this.jwtString = jwtString;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        return jwtString;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userResponseDto;
    }

    @Override
    public boolean isAuthenticated() {
        return userResponseDto!=null;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
