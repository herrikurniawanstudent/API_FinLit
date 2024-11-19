package com.skripsi.api.controller;

import com.skripsi.api.model.PreTest;
import com.skripsi.api.model.PreTestProgress;
import com.skripsi.api.model.User;
import com.skripsi.api.service.PreTestService;
import com.skripsi.api.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pre-tests")
public class PreTestController {
    @Autowired
    private ProgressService progressService;
    @Autowired
    private PreTestService preTestService;

    @GetMapping
    public ResponseEntity<List<PreTest>> getAllPreTests() {
        List<PreTest> preTests = preTestService.getAllPreTests();
        return ResponseEntity.ok(preTests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreTest> getPreTestById(@PathVariable Long id) {
        PreTest preTest = preTestService.getPreTestById(id);
        return ResponseEntity.ok(preTest);
    }

    @PostMapping
    public ResponseEntity<PreTest> createPreTest(@RequestBody PreTest preTest) {
        PreTest createdPreTest = preTestService.createPreTest(preTest);
        return ResponseEntity.ok(createdPreTest);
    }

    @PostMapping("/complete")
    public ResponseEntity<String> completePreTest(@AuthenticationPrincipal User user, @RequestBody PreTestProgress preTestProgress) {
        progressService.updatePreTestProgress(user, preTestProgress.isPreTestCompleted(), preTestProgress.getLastScore());
        return ResponseEntity.ok("Pre-test progress updated successfully");
    }

    @GetMapping("/progress")
    public ResponseEntity<?> getPreTestProgress(@AuthenticationPrincipal User user) {
        Optional<PreTestProgress> progress = progressService.getPreTestProgressByUserId(user.getId());
        return progress.map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}