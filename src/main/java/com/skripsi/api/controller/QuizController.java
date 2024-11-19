package com.skripsi.api.controller;

import com.skripsi.api.model.Quiz;
import com.skripsi.api.model.User;
import com.skripsi.api.service.ProgressService;
import com.skripsi.api.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private ProgressService progressService;

    @GetMapping("/submodule/{subModuleId}")
    public ResponseEntity<List<Quiz>> getQuizzesBySubModule(@PathVariable Long subModuleId) {
        List<Quiz> quizzes = quizService.getQuizzesBySubModuleId(subModuleId);
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/{subModuleId}/complete")
    public ResponseEntity<String> completeQuiz(@PathVariable Long subModuleId, @AuthenticationPrincipal User user) {
        progressService.markQuizCompleted(user.getId(), subModuleId);
        return ResponseEntity.ok("Quiz progress updated successfully");
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz createdQuiz = quizService.createQuiz(quiz);
        return ResponseEntity.ok(createdQuiz);
    }
}