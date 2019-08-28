package com.cpp.servicebooking.Request.UserRequest;

import javax.validation.constraints.NotBlank;

public class ServiceRequest {
    @NotBlank(message = "Role name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
