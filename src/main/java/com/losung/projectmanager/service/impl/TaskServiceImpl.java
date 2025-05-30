package com.losung.projectmanager.service.impl;

import com.losung.projectmanager.model.Task;
import com.losung.projectmanager.model.TaskStatus;
import com.losung.projectmanager.model.Team;
import com.losung.projectmanager.model.TeamMember;
import com.losung.projectmanager.repository.TaskRepository;
import com.losung.projectmanager.repository.TeamRepository;
import com.losung.projectmanager.repository.TeamMemberRepository;
import com.losung.projectmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TeamRepository teamRepository, TeamMemberRepository teamMemberRepository) {
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    @Override
    public List<TeamMember> findTeamMembersByProjectId(Long projectId) {
        Optional<Team> teamOpt = teamRepository.findByProjectId(projectId);
        if (teamOpt.isEmpty()) {
            throw new RuntimeException("No se encontró un equipo para el proyecto");
        }
        List<TeamMember> members = teamMemberRepository.findByTeamId(teamOpt.get().getId());
        if (members.isEmpty()) {
            throw new RuntimeException("El equipo no tiene miembros asignados");
        }
        return members;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    @Override
    public List<Task> findByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public Map<Long, List<Task>> findAllGroupedByProject() {
        return taskRepository.findAllGroupedByProject();
    }
} 