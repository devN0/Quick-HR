package com.reverb.quickhr.quickhr.user.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    @JsonProperty("Username")
    private String username;
    @JsonProperty("Email")
    private String email;
    private String token;
}
