package com.reverb.quickhr.quickhr.user.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
