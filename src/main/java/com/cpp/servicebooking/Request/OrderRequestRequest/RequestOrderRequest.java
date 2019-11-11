package com.cpp.servicebooking.Request.OrderRequestRequest;

import javax.validation.constraints.NotBlank;

public class RequestOrderRequest {

    @NotBlank(message = "title cannot be blank")
    private String servicetype;

    @NotBlank(message = "title cannot be blank")
    private String info;

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
