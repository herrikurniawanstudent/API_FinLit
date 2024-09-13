package com.skripsi.api.service;

import com.skripsi.api.model.Exam;
import com.skripsi.api.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public List<Exam> getAllFinalExams() {
        return examRepository.findAllByOrderByOrderNumberAsc();
    }

    public Exam getExamById(Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return exam;
    }

    public Exam createFinalExam(Exam exam) {
        return examRepository.save(exam);
    }
}
