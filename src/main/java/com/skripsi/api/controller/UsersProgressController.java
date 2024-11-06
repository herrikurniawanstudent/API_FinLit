package com.skripsi.api.controller;

import com.skripsi.api.model.User;
import com.skripsi.api.model.dto.UserProgressDto;
import com.skripsi.api.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-progress")
public class UsersProgressController {

    @Autowired
    private ProgressService progressService;
    
    @GetMapping
    public List<UserProgressDto> getAllUsersProgress() {
        return progressService.getAllUsersProgress();
    }

    @GetMapping("/{userId}")
    public UserProgressDto getUserProgressById(@PathVariable Long userId) {
        return progressService.getUserProgressById(userId);
    }
}
