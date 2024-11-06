package com.skripsi.api.repository;

import com.skripsi.api.model.PreTestProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreTestProgressRepository extends JpaRepository<PreTestProgress, Long> {
    Optional<PreTestProgress> findByUserId(Long userId);
}
