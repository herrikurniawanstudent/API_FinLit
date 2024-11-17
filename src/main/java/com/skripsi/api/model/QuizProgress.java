package com.skripsi.api.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "quiz_progress")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class QuizProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "submodule_id", nullable = false)
    @NonNull
    private SubModule subModule;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    @NonNull
    private Quiz quiz;

    @Column(name = "quiz_completed")
    private boolean quizCompleted = false; // Default value
}