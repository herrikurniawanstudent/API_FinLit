package com.skripsi.api.repository;

import com.skripsi.api.model.ExamProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamProgressRepository extends JpaRepository<ExamProgress, Long> {
    Optional<ExamProgress> findByUserId(Long userId);
}
