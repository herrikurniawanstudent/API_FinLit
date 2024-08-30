package com.skripsi.api.controller;

import com.skripsi.api.model.CourseModule;
import com.skripsi.api.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping
    public ResponseEntity<CourseModule> createModule(@RequestBody CourseModule module) {
        CourseModule createdModule = moduleService.createModule(module);
        return ResponseEntity.ok(createdModule);
    }

    @GetMapping
    public ResponseEntity<List<CourseModule>> getAllModules() {
        List<CourseModule> modules = moduleService.getAllModules();
        return ResponseEntity.ok(modules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseModule> getModuleById(@PathVariable Long id) {
        CourseModule module = moduleService.getModuleById(id);
        return ResponseEntity.ok(module);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseModule> updateModule(@PathVariable Long id, @RequestBody CourseModule moduleDetails) {
        CourseModule updatedModule = moduleService.updateModule(id, moduleDetails);
        return ResponseEntity.ok(updatedModule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }
}
