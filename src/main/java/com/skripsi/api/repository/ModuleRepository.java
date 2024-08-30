package com.skripsi.api.repository;

import com.skripsi.api.model.CourseModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository <CourseModule, Long> {
}
