package com.skripsi.api.repository;

import com.skripsi.api.model.QuizProgress;
import com.skripsi.api.model.SubModule;
import com.skripsi.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizProgressRepository extends JpaRepository <QuizProgress, Long> {
    Optional<QuizProgress> findByUserAndSubModule(User user, SubModule subModule);

    Optional<QuizProgress> findByUserIdAndSubModuleId(Long userId, Long subModuleId);

    List<QuizProgress> findByUserId(Long userId);
}
