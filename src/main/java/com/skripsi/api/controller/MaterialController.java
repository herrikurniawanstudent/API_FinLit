package com.skripsi.api.controller;

import com.skripsi.api.config.jwt.JwtService;
import com.skripsi.api.model.CourseModule;
import com.skripsi.api.model.Material;
import com.skripsi.api.model.SubModule;
import com.skripsi.api.model.User;
import com.skripsi.api.repository.UserRepository;
import com.skripsi.api.service.MaterialService;
import com.skripsi.api.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;
    @Autowired
    private ProgressService progressService;


    @PostMapping
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) {
        Material createdMaterial = materialService.createMaterial(material);
        return ResponseEntity.ok(createdMaterial);
    }

    @GetMapping
    public ResponseEntity<List<Material>> getAllMaterials() {
        List<Material> materials = materialService.getAllMaterials();
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterial(@PathVariable Long id, @AuthenticationPrincipal User user) {
        // Ambil material berdasarkan ID
        Material material = materialService.getMaterialById(id);

        // Ambil submodule dan module yang terkait dengan material
        SubModule subModule = material.getSubModule();
        CourseModule module = subModule.getModule();

        // Update progress ketika material diakses
        progressService.updateUserProgress(user, module, subModule, material, false); // false untuk quizCompleted jika quiz belum selesai

        return ResponseEntity.ok(material);
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getMaterialById(@PathVariable Long id) {
//        Material material = materialService.getMaterialById(id);
//        return ResponseEntity.ok(material);
//    }


    @GetMapping("/submodule/{subModuleId}")
    public ResponseEntity<List<Material>> getMaterialsBySubModuleId(@PathVariable Long subModuleId) {
        List<Material> materials = materialService.getMaterialsBySubModuleId(subModuleId);
        return ResponseEntity.ok(materials);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> updateMaterial(@PathVariable Long id, @RequestBody Material materialDetails) {
        Material updatedMaterial = materialService.updateMaterial(id, materialDetails);
        return ResponseEntity.ok(updatedMaterial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }
}
