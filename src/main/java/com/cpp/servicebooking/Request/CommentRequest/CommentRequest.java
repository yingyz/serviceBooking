package com.cpp.servicebooking.Request.CommentRequest;

import javax.validation.constraints.NotBlank;

public class CommentRequest {

    @NotBlank(message = "Detail cannot be blank")
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
