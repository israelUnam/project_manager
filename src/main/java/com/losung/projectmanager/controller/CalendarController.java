package com.losung.projectmanager.controller;

import com.losung.projectmanager.model.Project;
import com.losung.projectmanager.model.Task;
import com.losung.projectmanager.service.ProjectService;
import com.losung.projectmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/calendar")
    public String calendar(
            @RequestParam(value = "projectId", required = false) Long projectId,
            Model model) {
        List<Project> projects = projectService.findAll();
        Project selectedProject = null;
        List<Task> tasks = Collections.emptyList();

        if (!projects.isEmpty()) {
            if (projectId != null) {
                selectedProject = projects.stream()
                    .filter(p -> p.getId().equals(projectId))
                    .findFirst()
                    .orElse(projects.get(0));
            } else {
                selectedProject = projects.get(0);
            }
            tasks = taskService.findByProjectId(selectedProject.getId());
        }

        model.addAttribute("projects", projects);
        model.addAttribute("selectedProject", selectedProject);
        model.addAttribute("tasks", tasks);
        return "calendar";
    }
} 