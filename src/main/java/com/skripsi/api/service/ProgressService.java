package com.skripsi.api.service;

import com.skripsi.api.model.*;
import com.skripsi.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProgressService {
    @Autowired
    private MaterialProgressRepository materialProgressRepository;
    @Autowired
    private QuizProgressRepository quizProgressRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private SubModuleRepository subModuleRepository;
    @Autowired
    private ExamProgressRepository examProgressRepository;


    public void updateMaterialProgress(User user, CourseModule module, SubModule subModule, Material material) {
        Optional<MaterialProgress> existingProgressOpt = materialProgressRepository.findByUserAndSubModule(user, subModule);
        MaterialProgress progress;

        if (existingProgressOpt.isPresent()) {
            progress = existingProgressOpt.get();

            // Check if the new material's order number is greater than the current one
            if (progress.getLastCompletedMaterial() == null ||
                    material.getOrderNumber() > progress.getLastCompletedMaterial().getOrderNumber()) {
                progress.setLastCompletedMaterial(material);
            }

        } else {
            progress = new MaterialProgress();
            progress.setUser(user);
            progress.setModule(module);
            progress.setSubModule(subModule);
            progress.setLastCompletedMaterial(material);
        }

        materialProgressRepository.save(progress);
    }


    // Mark quiz as completed
    public void markQuizCompleted(Long userId, Long subModuleId) {
        Optional<QuizProgress> existingProgressOpt = quizProgressRepository.findByUserIdAndSubModuleId(userId, subModuleId);
        QuizProgress progress;

        if (existingProgressOpt.isPresent()) {
            progress = existingProgressOpt.get();
        } else {
            progress = new QuizProgress();
            progress.setUser(userRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));
            progress.setSubModule(subModuleRepository.findById(subModuleId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Submodule not found")));

            // Fetch the module from the submodule and set it in the QuizProgress
            CourseModule module = progress.getSubModule().getModule();
            progress.setModule(module);
        }

        progress.setQuizCompleted(true);
        quizProgressRepository.save(progress);
    }


    public void updateExamProgress(User user, boolean examCompleted, Integer lastScore, int totalPossibleScore) {
        Optional<ExamProgress> existingProgressOpt = examProgressRepository.findByUserId(user.getId());
        ExamProgress progress;

        if (existingProgressOpt.isPresent()) {
            progress = existingProgressOpt.get();
        } else {
            progress = new ExamProgress();
            progress.setUser(user);
        }

        progress.setExamCompleted(examCompleted);
        progress.setLastScore(lastScore);
        progress.setTotalPossibleScore(totalPossibleScore);
        examProgressRepository.save(progress);
    }

    public Optional<ExamProgress> getExamProgressByUserId(Long userId) {
        return examProgressRepository.findByUserId(userId);
    }
}

