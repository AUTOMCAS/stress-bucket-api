package com.stressbucket.stressbucketapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadReqestException extends RuntimeException{
    public BadReqestException(String message) {
        super(message);
    }
}
