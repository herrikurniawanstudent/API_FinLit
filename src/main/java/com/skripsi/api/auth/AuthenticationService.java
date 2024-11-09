package com.skripsi.api.auth;
import com.skripsi.api.auth.dto.AuthenticationRequest;
import com.skripsi.api.auth.dto.AuthenticationResponse;
import com.skripsi.api.auth.dto.RegisterRequest;
import com.skripsi.api.config.jwt.JwtService;
import com.skripsi.api.model.User;
import com.skripsi.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(User.Role.USER) // Set the role here
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        // In your authenticate method
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstname(user.getFirstname())
                .role(String.valueOf(user.getRole())) // Send role in response
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        // Assume `user.getRole()` returns the role as a String, e.g., "ADMIN" or "USER"
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstname(user.getFirstname())
                .role(String.valueOf(user.getRole())) // Set the role here
                .build();
    }

}

