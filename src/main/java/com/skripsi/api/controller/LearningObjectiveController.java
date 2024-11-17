package com.skripsi.api.controller;

import com.skripsi.api.model.LearningObjective;
import com.skripsi.api.model.User;
import com.skripsi.api.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/learning-objectives")
public class LearningObjectiveController {

    @Autowired
    private ProgressService progressService;

    @GetMapping("/submodule/{subModuleId}")
    public List<LearningObjective> getLearningObjectivesWithQuizCompletion(
            @PathVariable Long subModuleId,
            @AuthenticationPrincipal User user) {
        return progressService.getLearningObjectivesWithQuizCompletion(user.getId(), subModuleId);
    }
}