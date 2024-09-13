package com.skripsi.api.service;

import com.skripsi.api.model.CourseModule;
import com.skripsi.api.model.Material;
import com.skripsi.api.model.SubModule;
import com.skripsi.api.model.User;
import com.skripsi.api.repository.MaterialRepository;
import com.skripsi.api.repository.SubModuleRepository;
import com.skripsi.api.repository.UserProgressRepository;
import com.skripsi.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private SubModuleRepository subModuleRepository;
    @Autowired
    private ProgressService progressService;

    public Material createMaterial(Material material) {
        Long subModuleId = material.getSubModule().getId();
        Optional<SubModule> subModule = subModuleRepository.findById(subModuleId);
        if (subModule.isPresent()) {
            material.setSubModule(subModule.get());
            return materialRepository.save(material);
        } else {
            throw new IllegalArgumentException("SubModule with id " + subModuleId + " not found");
        }
    }

    public Material getMaterialAndUpdateProgress(Long materialId, User user) {
        // Fetch material by ID
        Material material =  materialRepository.findById(materialId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Get associated submodule and module
        SubModule subModule = material.getSubModule();
        CourseModule module = subModule.getModule();

        // Update user progress
        progressService.updateUserProgress(user, module, subModule, material, false);

        return material;
    }


    public List<Material> getMaterialsBySubModuleId(Long subModuleId) {
        subModuleRepository.findById(subModuleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return materialRepository.findBySubModuleIdOrderByOrderNumberAsc(subModuleId);
    }



}
