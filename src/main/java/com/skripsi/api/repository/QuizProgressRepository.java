package com.skripsi.api.repository;

import com.skripsi.api.model.QuizProgress;
import com.skripsi.api.model.SubModule;
import com.skripsi.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizProgressRepository extends JpaRepository<QuizProgress, Long> {

    @Query("SELECT COUNT(qp) FROM QuizProgress qp WHERE qp.user.id = :userId AND qp.subModule.id = :subModuleId AND qp.quizCompleted = true")
    int countCompletedQuizzesByUserIdAndSubModuleId(@Param("userId") Long userId, @Param("subModuleId") Long subModuleId);


    List<QuizProgress> findByUserId(Long userId);

    // Add this method for your requirement
    Optional<QuizProgress> findByUserIdAndQuizId(Long userId, Long quizId);
}

