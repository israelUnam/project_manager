package com.losung.projectmanager.service;

import com.losung.projectmanager.model.Task;
import com.losung.projectmanager.model.TaskStatus;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import com.losung.projectmanager.model.TeamMember;

public interface TaskService {
    List<Task> findAll();
    Optional<Task> findById(Long id);
    Task save(Task task);
    void deleteById(Long id);
    List<Task> findByProjectId(Long projectId);
    List<Task> findByStatus(TaskStatus status);
    Map<Long, List<Task>> findAllGroupedByProject();
    List<TeamMember> findTeamMembersByProjectId(Long projectId);
} 