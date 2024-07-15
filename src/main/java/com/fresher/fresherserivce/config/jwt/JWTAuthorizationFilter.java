package com.fresher.fresherserivce.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fresher.fresherserivce.service.JwtService;
import com.fresher.fresherserivce.vo.enums.Role;
import com.fresher.fresherserivce.vo.response.MessEntity;
import com.fresher.fresherserivce.vo.until.Common;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    private static final List<String> PUBLIC_URLS = Arrays.asList(
            "/auth/login",
            "/v3/api-docs",
            "/swagger-ui",
            "/swagger-ui.html",
            "/api/dashboard/test");

    @Override
    protected void doFilterInternal(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);

        String path = req.getRequestURI();
        // Bỏ qua kiểm tra token cho endpoint login
        if (isPublicUrl(path)) {
            chain.doFilter(req, res);
            return;
        }

        if (header == null || !header.startsWith("Bearer")) {
            setErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header", res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req, res);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        try {
            chain.doFilter(req, res);
        } catch (Exception e) {
            accessDeniedHandler();
        }

    }

    private boolean isPublicUrl(String path) {
        return PUBLIC_URLS.stream().anyMatch(path::startsWith);
    }

    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"statusCode\": 403, \"message\": \"Access Denied: You do not have the necessary permissions to access this resource.\"}");
        };
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
        Claims claims = jwtService.validateToken(token);

        if (claims == null) {
            setErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token", response);

        }
        if (claims != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (claims.get("role") != null) {
                List<Role> roles = Common.convertStringToRoles((String) claims.get("role"));
                List<SimpleGrantedAuthority> newAuthorities = roles.stream()
                        .map(role -> new SimpleGrantedAuthority(role.name()))
                        .collect(Collectors.toList());
                authorities.addAll(newAuthorities);
                // authorities.add(new SimpleGrantedAuthority((String) claims.get("role")));
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    claims.getSubject(), null, authorities);
            authentication.setDetails(claims);
            return authentication;
        }

        return null;
    }

    private void setErrorResponse(int statusCode, String message, HttpServletResponse response) throws IOException {
        MessEntity messEntity = new MessEntity(statusCode, message);
        response.setStatus(statusCode);
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(messEntity));
    }
}