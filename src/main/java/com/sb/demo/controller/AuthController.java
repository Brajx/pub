package com.sb.demo.controller;

import com.sb.demo.model.dto.LoginDto;
import com.sb.demo.model.dto.RegisterDto;
import com.sb.demo.response.JwtAuthResponse;
import com.sb.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String register = authService.register(registerDto);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){


        JwtAuthResponse response=authService.login(loginDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
