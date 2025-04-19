package com.losung.projectmanager.repository;

import com.losung.projectmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByProgramId(Long programId);
} 