package com.blood.registers.utils;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

    private HttpStatus code;

    public CustomException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }

    public CustomException(String message, Throwable cause, HttpStatus code) {
        super(message, cause);
        this.code = code;
    }

}
