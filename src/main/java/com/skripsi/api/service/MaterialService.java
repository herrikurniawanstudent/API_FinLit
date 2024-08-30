package com.skripsi.api.service;

import com.skripsi.api.model.Material;
import com.skripsi.api.model.SubModule;
import com.skripsi.api.repository.MaterialRepository;
import com.skripsi.api.repository.SubModuleRepository;
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

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Material getMaterialById(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Material> getMaterialsBySubModuleId(Long subModuleId) {
        subModuleRepository.findById(subModuleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return materialRepository.findBySubModuleIdOrderByOrderNumberAsc(subModuleId);
    }

    public Material updateMaterial(Long id, Material materialDetails) {
        Material material = getMaterialById(id);
        material.setName(materialDetails.getName());
        material.setContent(materialDetails.getContent());
        material.setOrderNumber(materialDetails.getOrderNumber());
        return materialRepository.save(material);
    }

    public void deleteMaterial(Long id) {
        Material material = getMaterialById(id);
        materialRepository.delete(material);
    }
}
