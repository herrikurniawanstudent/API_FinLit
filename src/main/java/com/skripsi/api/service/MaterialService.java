package com.skripsi.api.service;

import com.skripsi.api.model.Material;
import com.skripsi.api.model.SubModule;
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

//    public Material getMaterialByIdAndTrackProgress(Long materialId, Long userId) {
//        Material material = materialRepository.findById(materialId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        UserProgress progress = userProgressRepository.findByUserAndMaterial(user, material)
//                .orElse(new UserProgress());
//
//        progress.setUser(user);
//        progress.setMaterial(material);
//        progress.setCompleted(true);
//        userProgressRepository.save(progress);
//
//        return material;
//    }


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


//    public boolean canAccessMaterial(Long userId, Long materialId) {
//        Material material = materialRepository.findById(materialId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        // Find the previous material based on the order number
//        Optional<Material> previousMaterialOpt = materialRepository
//                .findFirstBySubModuleAndOrderNumberLessThanOrderByOrderNumberDesc(
//                        material.getSubModule(), material.getOrderNumber()
//                );
//
//        // If no previous material exists, the current material is the first one
//        if (previousMaterialOpt.isEmpty()) {
//            return true;
//        }
//
//        Material previousMaterial = previousMaterialOpt.get();
//
//        // Check if the previous material is completed
//        Optional<UserProgress> progressOpt = userProgressRepository.findByUserAndMaterial(user, previousMaterial);
//        return progressOpt.isPresent() && progressOpt.get().getCompleted();
//    }

}
