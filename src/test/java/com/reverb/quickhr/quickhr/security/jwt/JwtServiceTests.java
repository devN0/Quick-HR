package com.reverb.quickhr.quickhr.security.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtServiceTests {

    public JwtService jwtService() {
        return new JwtService();
    }

    @Test
    public void createJwt_works_with_username() {
        JwtService jwtService = jwtService();

        String jwtString = jwtService.createJwt("dev");
        String username = jwtService.getUsernameFromJwt(jwtString);

        assertEquals("dev", username);
    }
    @Test
    public void createJwt_errors_with_null() {
        JwtService jwtService = jwtService();

        Assertions.assertThrows(IllegalArgumentException.class, ()-> { //TDD
            String jwtString = jwtService.createJwt(null);
        });

    }
}
