package com.example.demo.Security;

public class SecurityConstants {
    public static final String SECRET = "q2as345d7c6r8vt79b5t3409up8hyi7t";
    public static final long EXPIRATION_TIME_OF_JWT = 864_000_000; // Потом сделаю 5 минут
    public static final long EXPIRATION_TIME_OF_RT = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_JWT_STRING = "JWToken";
    public static final String HEADER_EXPIRED_JWT_STRING = "ExpiredJWT";
    public static final String HEADER_RT_STRING = "RefreshToken";

}
