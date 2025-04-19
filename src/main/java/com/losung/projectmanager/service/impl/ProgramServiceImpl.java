package com.losung.projectmanager.service.impl;

import com.losung.projectmanager.model.Program;
import com.losung.projectmanager.repository.ProgramRepository;
import com.losung.projectmanager.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<Program> findAll() {
        return programRepository.findAll();
    }

    @Override
    public Optional<Program> findById(Long id) {
        return programRepository.findById(id);
    }

    @Override
    public Program save(Program program) {
        return programRepository.save(program);
    }

    @Override
    public void deleteById(Long id) {
        programRepository.deleteById(id);
    }
} 