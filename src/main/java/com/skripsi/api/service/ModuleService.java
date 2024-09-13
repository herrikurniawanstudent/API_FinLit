package com.skripsi.api.service;
import com.skripsi.api.model.*;
import com.skripsi.api.repository.ModuleRepository;
import com.skripsi.api.repository.UserProgressRepository;
import com.skripsi.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private UserProgressRepository userProgressRepository;


    public CourseModule createModule(CourseModule module) {
        return moduleRepository.save(module);
    }


    public List<CourseModule> getAllModulesWithProgress(Long userId) {
        // Fetch all modules
        List<CourseModule> modules = moduleRepository.findAll();

        // Fetch user progress
        List<UserProgress> userProgressList = userProgressRepository.findByUserId(userId);

        // Merge progress data with module data
        for (CourseModule module : modules) {
            for (SubModule subModule : module.getSubModules()) {
                // Find progress related to this submodule
                Optional<UserProgress> progress = userProgressList.stream()
                        .filter(up -> up.getSubModule().getId().equals(subModule.getId()))
                        .findFirst();

                // Add progress data to the submodule
                if (progress.isPresent()) {
                    UserProgress userProgress = progress.get();

                    // Set quiz completion status
                    subModule.setQuizCompleted(userProgress.isQuizCompleted());

                    // Get the last completed material ID (if available) id or ordernumber?
                    Integer lastCompletedMaterial = userProgress.getLastCompletedMaterial() != null
                            ? userProgress.getLastCompletedMaterial().getOrderNumber()
                            : null;

                    // Set the count of completed materials based on the last completed material
                    subModule.setCompletedMaterialsCount(lastCompletedMaterial);
                }
            }
        }

        return modules;
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
