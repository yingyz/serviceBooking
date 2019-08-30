package com.cpp.servicebooking.exceptions.Response;

public class RequestOrderNotFoundExceptionResponse {
    private String RequestNotFound;

    public RequestOrderNotFoundExceptionResponse(String requestNotFound) {
        RequestNotFound = requestNotFound;
    }

    public String getRequestNotFound() {
        return RequestNotFound;
    }

    public void setRequestNotFound(String requestNotFound) {
        RequestNotFound = requestNotFound;
    }
}
