package com.cpp.servicebooking.exceptions;

import com.cpp.servicebooking.exceptions.Exception.DuplicateAccountException;
import com.cpp.servicebooking.exceptions.Exception.DatabaseNotFoundException;
import com.cpp.servicebooking.exceptions.Exception.RequestOrderNotFoundException;
import com.cpp.servicebooking.exceptions.Response.DuplicateAccountReponse;
import com.cpp.servicebooking.exceptions.Response.DatabaseNotFoundExceptionResponse;
import com.cpp.servicebooking.exceptions.Response.RequestOrderNotFoundExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    public final ResponseEntity<Object> handleRequestOrderNotFoundException(RequestOrderNotFoundException ex, WebRequest request) {
        RequestOrderNotFoundExceptionResponse response = new RequestOrderNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
}
