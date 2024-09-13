package com.skripsi.api.controller;
import com.skripsi.api.model.Material;
import com.skripsi.api.model.User;
import com.skripsi.api.service.MaterialService;
import com.skripsi.api.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterial(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Material material = materialService.getMaterialAndUpdateProgress(id, user);
        return ResponseEntity.ok(material);
    }

    @GetMapping("/submodule/{subModuleId}")
    public ResponseEntity<List<Material>> getMaterialsBySubModuleId(@PathVariable Long subModuleId) {
        List<Material> materials = materialService.getMaterialsBySubModuleId(subModuleId);
        return ResponseEntity.ok(materials);
    }

}
