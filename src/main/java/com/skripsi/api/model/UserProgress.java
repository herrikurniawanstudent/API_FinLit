package com.skripsi.api.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "user_progress")
@Data
@NoArgsConstructor
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "current_material_id")
    private Material currentMaterial;

    private Boolean completed;

    @Column(name = "last_accessed")
    private Timestamp lastAccessed;

    // getters and setters
}
