package com.skripsi.api.repository;
import com.skripsi.api.model.CourseModule;
import com.skripsi.api.model.SubModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubModuleRepository extends JpaRepository<SubModule, Long > {
    List<SubModule> findByModule(CourseModule module);
}
