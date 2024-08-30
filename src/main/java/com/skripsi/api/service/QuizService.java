package com.skripsi.api.service;

import com.skripsi.api.model.Quiz;
import com.skripsi.api.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public List<Quiz> getQuizzesBySubModuleId(Long subModuleId) {
        return quizRepository.findBySubModuleIdOrderByOrderNumberAsc(subModuleId);
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
