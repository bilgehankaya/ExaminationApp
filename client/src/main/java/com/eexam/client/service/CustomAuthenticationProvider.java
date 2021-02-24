package com.eexam.client.service;

import java.util.ArrayList;
import java.util.List;

import com.eexam.client.dto.SigninRequest;
import com.eexam.client.dto.User;
import com.eexam.client.dto.JwtResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserServiceRest userServiceRest;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    
        String username = authentication.getName();
        System.out.println("Username: " + username);
        String password = authentication.getCredentials().toString();
        System.out.println("Password: " + password);

        SigninRequest request = SigninRequest.builder()
                                             .username(username)
                                             .password(password)
                                             .build();
        try {
            ResponseEntity<JwtResponse> response = userServiceRest.signin(request);
            if (response.getStatusCode().value() == HttpStatus.OK.value()) {
                System.out.println(response.getBody().getJwt());

                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                for(String role : response.getBody().getRoles()) {
                    authorities.add(new SimpleGrantedAuthority(role));
                }

                User authenticatedUser = User.builder()
                                             .id(response.getBody().getId())
                                             .username(response.getBody().getUsername())
                                             .email(response.getBody().getEmail())
                                             .jwt(response.getBody().getJwt())
                                             .roles(response.getBody().getRoles())
                                             .build();

                return new UsernamePasswordAuthenticationToken(authenticatedUser, password, authorities);
            } else {
                throw new BadCredentialsException("External system authentication failed");
            }

        } catch (Exception e) {
            throw new BadCredentialsException("External system authentication failed");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}