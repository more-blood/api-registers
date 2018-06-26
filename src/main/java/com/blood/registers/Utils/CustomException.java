package com.blood.registers.Utils;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

    HttpStatus code;

    public CustomException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }

    public CustomException(String message, Throwable cause, HttpStatus code) {
        super(message, cause);
        this.code = code;
    }

}
