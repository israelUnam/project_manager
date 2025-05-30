package com.losung.projectmanager.service.impl;

import com.losung.projectmanager.model.Team;
import com.losung.projectmanager.model.TeamMember;
import com.losung.projectmanager.model.Project;
import com.losung.projectmanager.model.Task;
import com.losung.projectmanager.repository.TeamRepository;
import com.losung.projectmanager.repository.TeamMemberRepository;
import com.losung.projectmanager.repository.ProjectRepository;
import com.losung.projectmanager.repository.TaskRepository;
import com.losung.projectmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, 
                         TeamMemberRepository teamMemberRepository,
                         ProjectRepository projectRepository,
                         TaskRepository taskRepository) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public List<TeamMember> findTeamMembers(Long teamId) {
        return teamMemberRepository.findByTeamId(teamId);
    }

    @Override
    public TeamMember saveTeamMember(TeamMember member) {
        return teamMemberRepository.save(member);
    }

    @Override
    public void deleteTeamMember(Long memberId) {
        if (hasAssignedTasks(memberId)) {
            throw new RuntimeException("No se puede eliminar el miembro del equipo porque tiene tareas asignadas");
        }
        teamMemberRepository.deleteById(memberId);
    }

    @Override
    public List<Project> findProjectsWithoutTeam() {
        List<Long> projectIdsWithTeam = teamRepository.findProjectIdsWithTeam();
        return projectRepository.findAll().stream()
                .filter(project -> !projectIdsWithTeam.contains(project.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TeamMember> findTeamMemberById(Long id) {
        return teamMemberRepository.findById(id);
    }

    @Override
    public boolean hasAssignedTasks(Long memberId) {
        TeamMember member = teamMemberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("Miembro del equipo no encontrado"));
        List<Task> assignedTasks = taskRepository.findByResponsable(member.getNombre());
        return !assignedTasks.isEmpty();
    }
} 