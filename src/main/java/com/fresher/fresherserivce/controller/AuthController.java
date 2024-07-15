package com.fresher.fresherserivce.controller;

import lombok.RequiredArgsConstructor;

import static com.fresher.fresherserivce.vo.response.ResponseEntity.responseToClient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.fresher.fresherserivce.service.AuthService;
import com.fresher.fresherserivce.vo.request.AuthRequest;
import com.fresher.fresherserivce.vo.request.RegisterRequest;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;


    /**
     *
     * @param request
     * @return
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PostMapping("/register")
    public Object register(@RequestBody RegisterRequest request) {
        Object resultObj =  authService.saveUser(request);
        return new ResponseEntity<>(responseToClient(resultObj), HttpStatus.OK);
    }

    /**
     *
     * @param authRequest
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.login(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    /**
     *
     * @return
     */
    @GetMapping("/validate")
    public Object validateToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getDetails();
    }
}
