package com.skripsi.api.model.dto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserProgressDto {

    private Long userId;
    private String userName;
    private List<MaterialProgressDto> materialProgressList = new ArrayList<>();
    private List<QuizProgressDto> quizProgressList = new ArrayList<>();
    private double overallProgressPercentage;

    public UserProgressDto(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public void addMaterialProgress(Long subModuleId, Integer lastCompletedOrder) {
        this.materialProgressList.add(new MaterialProgressDto(subModuleId, lastCompletedOrder));
    }

    public void addQuizProgress(Long subModuleId, boolean quizCompleted) {
        this.quizProgressList.add(new QuizProgressDto(subModuleId, quizCompleted));
    }

}




