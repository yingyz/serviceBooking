package com.cpp.servicebooking.Request.ServiceRequest;

import javax.validation.constraints.NotBlank;

public class ServiceProvideRequest {

    @NotBlank(message = "Detail cannot be blank")
    private String detail;

    @NotBlank(message = "Price cannot be blank")
    private String price;

    @NotBlank(message = "servicename cannot be blank")
    private String servicename;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }
}
