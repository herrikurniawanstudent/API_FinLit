package com.skripsi.api.repository;

import com.skripsi.api.model.LearningObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningObjectiveRepository extends JpaRepository<LearningObjective, Long> {
    List<LearningObjective> findBySubModuleId(Long subModuleId);
}
