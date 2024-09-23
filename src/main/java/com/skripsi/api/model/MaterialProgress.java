package com.skripsi.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "material_progress")
@Data
@NoArgsConstructor
public class MaterialProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private CourseModule module;

    @ManyToOne
    @JoinColumn(name = "submodule_id", nullable = false)
    private SubModule subModule;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material lastCompletedMaterial;
}
