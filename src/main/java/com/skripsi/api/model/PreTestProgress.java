package com.skripsi.api.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PreTestProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "pre_test_completed")
    private boolean preTestCompleted;

    @Column(name = "last_score")
    private Integer lastScore;

}