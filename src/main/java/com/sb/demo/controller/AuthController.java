package com.sb.demo.controller;

import com.sb.demo.model.dto.LoginDto;
import com.sb.demo.model.dto.RegisterDto;
import com.sb.demo.response.JwtAuthResponse;
import com.sb.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String register = authService.register(registerDto);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token =authService.login(loginDto);

        JwtAuthResponse response=JwtAuthResponse.builder().accessToken(token).build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
