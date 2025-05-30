package com.losung.projectmanager.controller;

import com.losung.projectmanager.model.Project;
import com.losung.projectmanager.model.ProjectStatus;
import com.losung.projectmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public String listProjects(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "project/list";
    }

    @PostMapping("/projects")
    public String createProject(@ModelAttribute Project project) {
        projectService.save(project);
        return "redirect:/projects";
    }

    @GetMapping("/projects/{id}")
    @ResponseBody
    public Project getProject(@PathVariable Long id) {
        return projectService.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }

    @PostMapping("/projects/{id}/update")
    public String updateProject(@PathVariable Long id, @ModelAttribute Project project) {
        project.setId(id);
        projectService.save(project);
        return "redirect:/projects";
    }

    @PostMapping("/projects/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteById(id);
        return "redirect:/projects";
    }

    @ModelAttribute("projectStatuses")
    public ProjectStatus[] projectStatuses() {
        return ProjectStatus.values();
    }
}