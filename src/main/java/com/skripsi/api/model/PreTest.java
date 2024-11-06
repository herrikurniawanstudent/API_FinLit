package com.skripsi.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PreTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @Column(columnDefinition = "TEXT")
    private String options;

    @Column(name = "correct_answer")
    private String correctAnswer;

    private Integer score;

    @Column(name = "order_number")
    private Integer orderNumber;
}
