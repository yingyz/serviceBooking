package com.cpp.servicebooking.Request.OrderRequestRequest;

import javax.validation.constraints.NotBlank;

public class RequestOrderRequest {

    @NotBlank(message = "title cannot be blank")
    private String title;

    @NotBlank(message = "title cannot be blank")
    private String info;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
