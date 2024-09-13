package com.skripsi.api.service;

import com.skripsi.api.model.*;
import com.skripsi.api.repository.MaterialRepository;
import com.skripsi.api.repository.SubModuleRepository;
import com.skripsi.api.repository.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProgressService {

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private SubModuleRepository subModuleRepository;


    public void updateUserProgress(User user, CourseModule module, SubModule subModule, Material material, boolean quizCompleted) {
        Optional<UserProgress> existingProgressOpt = userProgressRepository.findByUserAndSubModule(user, subModule);
        UserProgress progress;

        if (existingProgressOpt.isPresent()) {
            progress = existingProgressOpt.get();

            // Check if the new material's order number is greater than the current one
            if (progress.getLastCompletedMaterial() == null ||
                    material.getOrderNumber() > progress.getLastCompletedMaterial().getOrderNumber()) {
                progress.setLastCompletedMaterial(material);
            }

        } else {
            progress = new UserProgress();
            progress.setUser(user);
            progress.setModule(module);
            progress.setSubModule(subModule);
            progress.setLastCompletedMaterial(material);
            progress.setQuizCompleted(quizCompleted);  // Set quiz completion status during new progress creation
        }

        // Save progress to the repository
        userProgressRepository.save(progress);
    }


    // Mark quiz as completed
    public void markQuizCompleted(Long userId, Long subModuleId) {
        UserProgress progress = userProgressRepository.findByUserIdAndSubModuleId(userId, subModuleId)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
        progress.setQuizCompleted(true);
        userProgressRepository.save(progress);
    }
}

