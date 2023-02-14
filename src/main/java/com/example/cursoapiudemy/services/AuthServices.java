package com.example.cursoapiudemy.services;

import com.example.cursoapiudemy.data.vo.v1.security.AccountCredentialsVO;
import com.example.cursoapiudemy.data.vo.v1.security.TokenVO;
import com.example.cursoapiudemy.model.User;
import com.example.cursoapiudemy.repository.UserRepository;
import com.example.cursoapiudemy.security.Jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    public ResponseEntity singin(AccountCredentialsVO data) {
        try {
            String userName = data.getUserName();
            String password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            User user = repository.findByUsername(userName);
            TokenVO tokenResponse = new TokenVO();
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(userName, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + userName + " not found!");
            }

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    public ResponseEntity refreshToken(String username, String refreshToken) {

        User user = repository.findByUsername(username);
        TokenVO tokenResponse = new TokenVO();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }

        return ResponseEntity.ok(tokenResponse);
    }
}
