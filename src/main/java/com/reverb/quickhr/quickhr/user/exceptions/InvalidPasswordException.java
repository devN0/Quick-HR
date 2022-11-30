package com.reverb.quickhr.quickhr.user.exceptions;

public class InvalidPasswordException extends IllegalArgumentException {
    public InvalidPasswordException() {
        super("Incorrect password. Please enter the right password.");
    }
}
