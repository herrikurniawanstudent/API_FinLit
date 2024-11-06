package com.skripsi.api.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PreTestProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private PreTest preTest;

    private boolean preTestCompleted;
    private Integer lastScore;

}