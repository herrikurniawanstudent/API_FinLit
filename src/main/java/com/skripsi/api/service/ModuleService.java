package com.skripsi.api.service;
import com.skripsi.api.model.CourseModule;
import com.skripsi.api.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;


    public CourseModule createModule(CourseModule module) {
        return moduleRepository.save(module);
    }

    public List<CourseModule> getAllModules() {
        return moduleRepository.findAll();
    }

    public CourseModule getModuleById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public CourseModule updateModule(Long id, CourseModule moduleDetails) {
        CourseModule module = getModuleById(id);
        module.setName(moduleDetails.getName());
        module.setDescription(moduleDetails.getDescription());
        return moduleRepository.save(module);
    }

    public void deleteModule(Long id) {
        CourseModule module = getModuleById(id);
        moduleRepository.delete(module);
    }
}
