package com.sb.demo.service;

import com.sb.demo.model.dto.LoginDto;
import com.sb.demo.model.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
