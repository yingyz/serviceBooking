package com.cpp.servicebooking.exceptions.Response;

public class DuplicateCommentExceptionResponse {
    private String CommentName;

    public DuplicateCommentExceptionResponse(String commentName) {
        CommentName = commentName;
    }

    public String getCommentName() {
        return CommentName;
    }

    public void setCommentName(String commentName) {
        CommentName = commentName;
    }
}
