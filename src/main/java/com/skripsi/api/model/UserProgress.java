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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Reference to the user

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private CourseModule module;  // Current module the user is working on

    @ManyToOne
    @JoinColumn(name = "submodule_id", nullable = false)
    private SubModule subModule;  // Current submodule the user is working on

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material lastCompletedMaterial;  // The last completed material

    @Column(name = "quiz_completed")
    private boolean quizCompleted;  // Whether the quiz for the submodule is completed or not
}

