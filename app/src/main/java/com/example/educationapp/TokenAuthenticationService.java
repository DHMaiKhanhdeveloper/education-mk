package com.example.educationapp;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

    private static final long EXPIRATIONTIME = 864_000_000; // 10 days
    private static final String SECRET = "ThisIsASecret";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";
//    public static void addAuthentication(HttpServletResponse res, String username) {
//        String JWT = Jwts.builder()
//                .setSubject(username)
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
//                .signWith(SignatureAlgorithm.HS256, SECRET)
//                .compact();
//        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
//    }
}
