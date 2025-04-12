package com.losung.projectmanager.controller;

import com.losung.projectmanager.model.Program;
import com.losung.projectmanager.model.ProgramStatus;
import com.losung.projectmanager.model.Project;
import com.losung.projectmanager.model.ProjectStatus;
import com.losung.projectmanager.service.ProgramService;
import com.losung.projectmanager.service.ProjectService;
import com.losung.projectmanager.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramService programService;
    private final ProjectService projectService;
    private final PortfolioService portfolioService;

    @Autowired
    public ProgramController(ProgramService programService, 
                           ProjectService projectService,
                           PortfolioService portfolioService) {
        this.programService = programService;
        this.projectService = projectService;
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public String listPrograms(Model model) {
        List<Program> programs = programService.findAll();
        model.addAttribute("programs", programs);
        model.addAttribute("portfolios", portfolioService.findAll());
        return "program/list";
    }

    @PostMapping
    public String createProgram(@ModelAttribute Program program) {
        programService.save(program);
        return "redirect:/programs";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Program getProgram(@PathVariable Long id) {
        return programService.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));
    }

    @PostMapping("/{id}/update")
    public String updateProgram(@PathVariable Long id, @ModelAttribute Program program) {
        program.setId(id);
        programService.save(program);
        return "redirect:/programs";
    }

    @DeleteMapping("/{id}")
    public String deleteProgram(@PathVariable Long id) {
        programService.deleteById(id);
        return "redirect:/programs";
    }

    @GetMapping("/{id}/proyectos")
    @ResponseBody
    public List<Project> getProgramProjects(@PathVariable Long id) {
        return projectService.findByProgramId(id);
    }

    @ModelAttribute("program")
    public Program program() {
        return new Program();
    }

    @ModelAttribute("programStatuses")
    public ProgramStatus[] programStatuses() {
        return ProgramStatus.values();
    }

    @ModelAttribute("projectStatuses")
    public ProjectStatus[] projectStatuses() {
        return ProjectStatus.values();
    }
} 