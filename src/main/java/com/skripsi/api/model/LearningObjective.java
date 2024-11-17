package com.skripsi.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class LearningObjective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String objectiveDescription;

    @ManyToOne
    @JoinColumn(name = "submodule_id", nullable = false)
    @JsonBackReference
    private SubModule subModule;

    @ManyToMany
    @JoinTable(
            name = "objective_quiz",
            joinColumns = @JoinColumn(name = "objective_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id")
    )
    private List<Quiz> quizzes;

    @Transient
    private boolean quizCompleted;
}

