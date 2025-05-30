package com.losung.projectmanager.controller;

import com.losung.projectmanager.model.Task;
import com.losung.projectmanager.model.TaskStatus;
import com.losung.projectmanager.model.Project;
import com.losung.projectmanager.model.TeamMember;
import com.losung.projectmanager.service.TaskService;
import com.losung.projectmanager.service.ProjectService;
import com.losung.projectmanager.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final TeamRepository teamRepository;

    @Autowired
    public TaskController(TaskService taskService, ProjectService projectService, TeamRepository teamRepository) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public String listTasks(Model model) {
        Map<Long, List<Task>> tasksByProject = taskService.findAllGroupedByProject();
        List<Project> projects = projectService.findAll();
        List<Long> projectsWithTeamMembers = teamRepository.findProjectIdsWithTeamMembers();
        
        model.addAttribute("tasksByProject", tasksByProject);
        model.addAttribute("projects", projects);
        model.addAttribute("projectsWithTeamMembers", projectsWithTeamMembers);
        model.addAttribute("taskStatuses", TaskStatus.values());
        return "task/list";
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Task getTask(@PathVariable Long id) {
        return taskService.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
    }

    @GetMapping("/project/{projectId}/members")
    @ResponseBody
    public List<TeamMember> getProjectMembers(@PathVariable Long projectId) {
        return taskService.findTeamMembersByProjectId(projectId);
    }

    @PostMapping("/{id}/update")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.save(task);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "redirect:/tasks";
    }
} 