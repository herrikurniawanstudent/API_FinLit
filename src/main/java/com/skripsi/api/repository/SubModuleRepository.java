package com.skripsi.api.repository;
import com.skripsi.api.model.CourseModule;
import com.skripsi.api.model.SubModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubModuleRepository extends JpaRepository<SubModule, Long > {
    List<SubModule> findByModule(CourseModule module);

    @Query("SELECT s FROM SubModule s WHERE s.module.id = :moduleId AND s.orderNumber = :nextOrderNumber")
    Optional<SubModule> findNextSubModule(Long moduleId, Integer nextOrderNumber);
}
