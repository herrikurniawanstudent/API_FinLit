package com.skripsi.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizProgressDto {
    private Long subModuleId;
    private boolean quizCompleted;
}
