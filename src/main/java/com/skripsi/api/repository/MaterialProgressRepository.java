package com.skripsi.api.repository;

import com.skripsi.api.model.MaterialProgress;
import com.skripsi.api.model.SubModule;
import com.skripsi.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialProgressRepository extends JpaRepository <MaterialProgress, Long> {
    Optional<MaterialProgress> findByUserAndSubModule(User user, SubModule subModule);

    List<MaterialProgress> findByUserId(Long userId);
}
