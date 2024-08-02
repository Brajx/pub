package com.sb.demo.service.impl;

import com.sb.demo.exception.CustomerRegException;
import com.sb.demo.model.Role;
import com.sb.demo.model.User;
import com.sb.demo.model.dto.LoginDto;
import com.sb.demo.model.dto.RegisterDto;
import com.sb.demo.repository.rolerepo.RoleRepository;
import com.sb.demo.repository.userrepo.UserRepository;
import com.sb.demo.security.JwtTokenProvider;
import com.sb.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImp implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {


        // check username is already exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new CustomerRegException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // check email is already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new CustomerRegException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        Set<Role> role=new HashSet<>();
        Role role1 =roleRepository.findByName("ROLE_USER");
        role.add(role1);

        User user = User.builder()
                .username(registerDto.getUsername())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .roles(role)
                .build();

        userRepository.save(user);

        return "User registerd succesfully";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtTokenProvider.generateToken(authentication);
        return token;
    }
}
