package com.skripsi.api.controller;

import com.skripsi.api.model.Exam;
import com.skripsi.api.model.ExamProgress;
import com.skripsi.api.model.User;
import com.skripsi.api.service.ExamService;
import com.skripsi.api.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private ProgressService progressService;

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examService.getAllFinalExams();
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        Exam exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam createdExam = examService.createFinalExam(exam);
        return ResponseEntity.ok(createdExam);
    }

    @PostMapping("/complete")
    public ResponseEntity<String> completeExam(@AuthenticationPrincipal User user, @RequestBody ExamProgress examProgress) {
        progressService.updateExamProgress(user, examProgress.isExamCompleted(), examProgress.getLastScore());
        return ResponseEntity.ok("Exam progress updated successfully");
    }

    @GetMapping("/progress")
    public ResponseEntity<?> getExamProgress(@AuthenticationPrincipal User user) {
        Optional<ExamProgress> progress = progressService.getExamProgressByUserId(user.getId());
        return progress.map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}