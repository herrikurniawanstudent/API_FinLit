package com.skripsi.api.controller;

import com.skripsi.api.model.Exam;
import com.skripsi.api.model.Quiz;
import com.skripsi.api.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping
    public List<Exam> getAllExams () {
        return examService.getAllFinalExams();
    }

    @GetMapping("/{id}")
    public Exam getExamById (@PathVariable Long id) {
        return examService.getExamById(id);
    }

    @PostMapping
    public Exam createExam (@RequestBody Exam exam) {
        return examService.createFinalExam(exam);
    }
}
