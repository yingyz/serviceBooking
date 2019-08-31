package com.cpp.servicebooking.exceptions;

import com.cpp.servicebooking.exceptions.Exception.*;
import com.cpp.servicebooking.exceptions.Response.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.ws.WebEndpoint;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleDuplicateAccountException(DuplicateAccountException ex, WebRequest request) {
        DuplicateAccountReponse reponse = new DuplicateAccountReponse(ex.getMessage());
        return new ResponseEntity<Object>(reponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleDatabaseNotFoundException(DatabaseNotFoundException ex, WebRequest request) {
        DatabaseNotFoundExceptionResponse response = new DatabaseNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleRequestOrderNotFoundException(RequestOrderNotFoundException ex, WebRequest request) {
        RequestOrderNotFoundExceptionResponse response = new RequestOrderNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleDuplicateCommentException(DuplicateCommentException ex, WebRequest request) {
        DuplicateCommentExceptionResponse response = new DuplicateCommentExceptionResponse(ex.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public final ResponseEntity<Object> handleCommentNotFoundException(CommentNotFoundException ex, WebRequest request) {
        CommentNotFoundExceptionResponse response = new CommentNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
}
