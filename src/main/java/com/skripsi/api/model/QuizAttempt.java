package com.skripsi.api.model;

import jakarta.persistence.*;

@Entity
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private boolean isPassed;

    @Column(nullable = false)
    private boolean isRetakeAllowed = false; // Default to false
}

