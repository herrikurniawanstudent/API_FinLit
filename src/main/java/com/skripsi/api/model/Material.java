package com.skripsi.api.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "materials")
@Data
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submodule_id", nullable = false)
    @JsonBackReference
    private SubModule subModule;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "created_at")
    private Timestamp createdAt;

}


