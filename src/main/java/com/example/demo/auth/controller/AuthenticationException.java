package com.example.demo.auth.controller;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Wrong username or password!");
    }

    public AuthenticationException(String msg) {
        super(msg);
    }

}
