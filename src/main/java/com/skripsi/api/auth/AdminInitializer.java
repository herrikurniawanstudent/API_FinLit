package com.skripsi.api.auth;

import com.skripsi.api.model.User;
import com.skripsi.api.model.User.Role;
import com.skripsi.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            User admin = User.builder()
                    .firstname("Admin")
                    .lastname("User")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("securePassword")) // Set a secure password
                    .role(Role.ADMIN) // Use the Role enum from User class
                    .build();
            userRepository.save(admin);
            System.out.println("Admin user created with email: admin@example.com and predefined password.");
        }
    }
}