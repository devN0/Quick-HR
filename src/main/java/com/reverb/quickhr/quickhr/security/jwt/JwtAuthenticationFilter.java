package com.reverb.quickhr.quickhr.security.jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class JwtAuthenticationFilter extends AuthenticationFilter {
    public JwtAuthenticationFilter(JwtAuthenticationManager jwtAuthenticationManager) {
        super(jwtAuthenticationManager, new JwtAuthenticationConverter());
        this.setSuccessHandler(((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }));
    }
}
