package com.reverb.quickhr.quickhr.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String secretKey = "hfT5nY00Drjng043486ht3j39E@ljknt3";
    Algorithm algo = Algorithm.HMAC256(secretKey);

    public String createJwt(String username) {
        if(username == null) {
            throw new IllegalArgumentException();
        }
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .sign(algo);
    }

    public String getUsernameFromJwt(String token) {
        return JWT.require(algo)
                .build()
                .verify(token)
                .getSubject();
    }
}
