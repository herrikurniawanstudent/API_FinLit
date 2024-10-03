package com.skripsi.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exam_progress")
@Data
@NoArgsConstructor
public class ExamProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "exam_completed")
    private boolean examCompleted;

    @Column(name = "last_score")
    private Integer lastScore;

    @Column(name = "total_possible_score")
    private Integer totalPossibleScore;
}
