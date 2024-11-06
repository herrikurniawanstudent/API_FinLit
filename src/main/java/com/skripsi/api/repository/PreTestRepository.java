package com.skripsi.api.repository;

import com.skripsi.api.model.Exam;
import com.skripsi.api.model.PreTest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PreTestRepository extends JpaRepository<PreTest, Long> {
    List<Exam> findAllByOrderByOrderNumberAsc();
}
