package com.cpp.servicebooking.Request.UserRequest;

import javax.validation.constraints.NotBlank;

public class SignUpRequest {
    @NotBlank(message = "Email cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "firstname cannot be blank")
    private String firstname;

    @NotBlank(message = "lastname cannot be blank")
    private String lastname;

    @NotBlank(message = "streetname cannot be blank")
    private String streetname;

    @NotBlank(message = "city cannot be blank")
    private String city;

    @NotBlank(message = "state cannot be blank")
    private String state;

    @NotBlank(message = "zipcode cannot be blank")
    private String zipcode;

    @NotBlank(message = "phone cannot be blank")
    private String phone;

    @NotBlank(message = "Role cannot be blank")
    private String role;

    private String servicename;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }
}
