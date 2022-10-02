package com.bravi.portfolio.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bravi.portfolio.dto.LoginRequest;
import com.bravi.portfolio.dto.LoginResponse;
import com.bravi.portfolio.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    public static final int THIRTY_MINUTES = 1000 * 60 * 30;
    private AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return LoginResponse.builder()
                .token(getToken(userDetails))
                .build();
    }

    private String getToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + THIRTY_MINUTES))
                .sign(algorithm);
    }
}
