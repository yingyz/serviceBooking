package com.cpp.servicebooking.exceptions;

public class DuplicateAccountReponse {
    private String username;
    public DuplicateAccountReponse(String username) {this.username = username;}
    public String getUsername() {return this.username;}
    public void setUsername(String username) {this.username = username;}
}
