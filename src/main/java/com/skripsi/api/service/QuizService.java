package com.skripsi.api.service;

import com.skripsi.api.model.Quiz;
import com.skripsi.api.model.User;
import com.skripsi.api.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ProgressService progressService;


    public List<Quiz> getQuizzesBySubModuleId(Long subModuleId) {
        return quizRepository.findBySubModuleIdOrderByOrderNumberAsc(subModuleId);
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
