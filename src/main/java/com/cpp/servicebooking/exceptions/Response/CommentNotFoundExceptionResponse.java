package com.cpp.servicebooking.exceptions.Response;

public class CommentNotFoundExceptionResponse {
    private String CommentNotFound;

    public CommentNotFoundExceptionResponse(String commentNotFound) {
        CommentNotFound = commentNotFound;
    }

    public String getCommentNotFound() {
        return CommentNotFound;
    }

    public void setCommentNotFound(String commentNotFound) {
        CommentNotFound = commentNotFound;
    }
}
