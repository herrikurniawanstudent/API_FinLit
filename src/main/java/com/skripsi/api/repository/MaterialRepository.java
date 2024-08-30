package com.skripsi.api.repository;

import com.skripsi.api.model.Material;
import com.skripsi.api.model.SubModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository <Material, Long> {
    List<Material> findBySubModuleIdOrderByOrderNumberAsc(Long subModuleId);
}
