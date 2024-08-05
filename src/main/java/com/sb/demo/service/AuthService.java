package com.sb.demo.service;

import com.sb.demo.model.dto.LoginDto;
import com.sb.demo.model.dto.RegisterDto;
import com.sb.demo.response.JwtAuthResponse;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
