package com.skripsi.api.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quiz_progress")
@Data
@NoArgsConstructor
public class QuizProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "submodule_id", nullable = false)
    private SubModule subModule;

    @Column(name = "quiz_completed")
    private boolean quizCompleted;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz; // Add this relationship
}
