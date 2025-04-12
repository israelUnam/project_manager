package com.losung.projectmanager.service;

import com.losung.projectmanager.model.Project;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> findAll();
    Optional<Project> findById(Long id);
    Project save(Project project);
    void deleteById(Long id);
    List<Project> findByProgramId(Long programId);
} 