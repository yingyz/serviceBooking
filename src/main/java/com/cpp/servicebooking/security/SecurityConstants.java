package com.cpp.servicebooking.security;

public class SecurityConstants {
    public static final String[] ADMIN_URLS = {"/api/admin/**"};
    public static final String[] SIGN_UP_URLS = {"/api/users/**"};
    public static final String[] SERVICE = {"/api/provider/update", "/api/request/all/**","/api/comment/post/**"};
    public static final String[] CUSTOMER = {"/api/request", "/api/request/", "/api/request/me/**", "/api/request/id/**"};
    public static final String SECRET ="HenrySecretToken";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 3600000;
}
