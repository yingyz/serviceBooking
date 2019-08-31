package com.cpp.servicebooking.exceptions.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateCommentException extends RuntimeException{
    public DuplicateCommentException(String message) {
        super(message);
    }
}
