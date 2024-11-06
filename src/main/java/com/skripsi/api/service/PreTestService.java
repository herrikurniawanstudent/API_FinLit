package com.skripsi.api.service;

import com.skripsi.api.model.PreTest;
import com.skripsi.api.repository.PreTestProgressRepository;
import com.skripsi.api.repository.PreTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreTestService {
    @Autowired
    private PreTestRepository preTestRepository;
    @Autowired
    private PreTestProgressRepository preTestProgressRepository;

    public List<PreTest> getAllPreTests() {
        return preTestRepository.findAll();
    }

    public PreTest getPreTestById(Long id) {
        return preTestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PreTest not found"));
    }

    public PreTest createPreTest(PreTest preTest) {
        return preTestRepository.save(preTest);
    }
}
