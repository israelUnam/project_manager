package com.losung.projectmanager.service;

import com.losung.projectmanager.model.Program;
import java.util.List;
import java.util.Optional;

public interface ProgramService {
    List<Program> findAll();
    Optional<Program> findById(Long id);
    Program save(Program program);
    void deleteById(Long id);
} 