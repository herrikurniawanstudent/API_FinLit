package com.skripsi.api.controller;

import com.skripsi.api.auth.AuthenticationService;
import com.skripsi.api.model.User;
import com.skripsi.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me") // New endpoint to get current user's info
    public User getCurrentUser(@AuthenticationPrincipal User user) {
        return user;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin-resource")
    public ResponseEntity<String> getAdminResource() {
        return ResponseEntity.ok("Admin-specific data");
    }

}
