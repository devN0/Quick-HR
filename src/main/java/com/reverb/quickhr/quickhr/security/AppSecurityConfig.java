package com.reverb.quickhr.quickhr.security;

import com.reverb.quickhr.quickhr.security.jwt.JwtAuthenticationFilter;
import com.reverb.quickhr.quickhr.security.jwt.JwtAuthenticationManager;
import com.reverb.quickhr.quickhr.security.jwt.JwtService;
import com.reverb.quickhr.quickhr.user.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public AppSecurityConfig(JwtService jwtService, UserService userService) {
        jwtAuthenticationFilter = new JwtAuthenticationFilter(
                new JwtAuthenticationManager(jwtService, userService)
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/about").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST,"/user","/user/login").permitAll()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        super.configure(http);
    }
}
