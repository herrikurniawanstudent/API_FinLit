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

    // Retrieve user progress based on user and submodule
    public Optional<UserProgress> getUserProgress(User user, SubModule subModule) {
        return userProgressRepository.findByUserAndSubModule(user, subModule);
    }

    // Update the user's progress
    public UserProgress updateUserProgress(User user, CourseModule module, SubModule subModule, Material material, boolean quizCompleted) {
        // Find existing progress for the user and submodule
        Optional<UserProgress> existingProgress = userProgressRepository.findByUserAndSubModule(user, subModule);

        UserProgress progress;

        // If progress exists, update it, otherwise create a new one
        if (existingProgress.isPresent()) {
            progress = existingProgress.get();
        } else {
            progress = new UserProgress();
            progress.setUser(user); // Directly set the User object
            progress.setModule(module); // Set the module
            progress.setSubModule(subModule); // Set the submodule
        }

        // Update progress with last completed material and quiz status
        progress.setLastCompletedMaterial(material);
        progress.setQuizCompleted(quizCompleted);

        // Save progress to the repository
        return userProgressRepository.save(progress);
    }



    // Check if all materials in the submodule are completed
    private boolean checkIfAllMaterialsCompleted(SubModule subModule) {
        return subModule.getMaterials().stream()
                .allMatch(material -> material.getOrderNumber() <= subModule.getMaterials().size());
    }

    // Unlock the next submodule
    private void unlockNextSubModule(SubModule subModule, UserProgress progress) {
        subModuleRepository.findNextSubModule(subModule.getModule().getId(), subModule.getOrderNumber() + 1)
                .ifPresent(nextSubModule -> progress.setSubModule(nextSubModule));
    }

    // Mark quiz as completed
    public void markQuizCompleted(Long userId, Long subModuleId) {
        UserProgress progress = userProgressRepository.findByUserIdAndSubModuleId(userId, subModuleId)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
        progress.setQuizCompleted(true);
        userProgressRepository.save(progress);
    }
}

