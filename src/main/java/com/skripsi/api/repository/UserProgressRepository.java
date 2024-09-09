package com.skripsi.api.repository;

import com.skripsi.api.model.SubModule;
import com.skripsi.api.model.User;
import com.skripsi.api.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
    List<UserProgress> findByUserId(Long userId);

    Optional<UserProgress> findByUserAndSubModule(User user, SubModule subModule);

    Optional<UserProgress> findByUserIdAndSubModuleId(Long userId, Long subModuleId);
}

