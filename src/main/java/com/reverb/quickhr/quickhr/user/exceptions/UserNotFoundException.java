package com.reverb.quickhr.quickhr.user.exceptions;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException() {
        super("No such user exist. Check the credentials");
    }
}
