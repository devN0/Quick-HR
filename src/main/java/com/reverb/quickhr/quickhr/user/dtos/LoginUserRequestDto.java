package com.reverb.quickhr.quickhr.user.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginUserRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
