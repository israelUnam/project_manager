package com.losung.projectmanager.service;

import com.losung.projectmanager.model.Team;
import com.losung.projectmanager.model.TeamMember;
import com.losung.projectmanager.model.Project;
import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> findAll();
    Optional<Team> findById(Long id);
    Team save(Team team);
    void deleteById(Long id);
    List<TeamMember> findTeamMembers(Long teamId);
    TeamMember saveTeamMember(TeamMember member);
    void deleteTeamMember(Long memberId);
    List<Project> findProjectsWithoutTeam();
    Optional<TeamMember> findTeamMemberById(Long id);
    boolean hasAssignedTasks(Long memberId);
} 