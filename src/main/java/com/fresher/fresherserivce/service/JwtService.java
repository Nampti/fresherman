package com.fresher.fresherserivce.service;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fresher.fresherserivce.exception.HandleException;
import com.fresher.fresherserivce.model.User;
import com.fresher.fresherserivce.vo.until.Common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt-secret}")
    private String JWT_SECRET;

    public Claims validateToken(String token) {
        try {
            return (Claims) Jwts.parser().setSigningKey(JWT_SECRET).parse(token).getBody();
        } catch (SignatureException e) {
            logger.error(e.getMessage());
            return null;
        } catch (ExpiredJwtException e) {
            logger.error(e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        return createToken(claims, user.getUsername());
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
}
