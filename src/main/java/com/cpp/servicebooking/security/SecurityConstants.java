package com.cpp.servicebooking.security;

public class SecurityConstants {
    public static final String[] ADMIN_URLS = {"/api/admin/**", "/api/users/all"};
    public static final String[] SIGN_UP_URLS = {"/api/users/register", "/api/users/login", "/api/users/me"};
    public static final String[] SERVICE = {"/api/provide/update"};
    public static final String POSTS = "/api/posts/**";
    public static final String SECRET ="HenrySecretToken";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 3600000;
}
