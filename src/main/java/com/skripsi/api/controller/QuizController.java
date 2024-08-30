package com.skripsi.api.controller;

import com.skripsi.api.model.Quiz;
import com.skripsi.api.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/submodule/{subModuleId}")
    public List<Quiz> getQuizzesBySubModule(@PathVariable Long subModuleId) {
        return quizService.getQuizzesBySubModuleId(subModuleId);
    }

    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }
}
