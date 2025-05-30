package com.losung.projectmanager.repository;

import com.losung.projectmanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t.project.id FROM Team t")
    List<Long> findProjectIdsWithTeam();

    @Query("SELECT DISTINCT t.project.id FROM Team t WHERE EXISTS (SELECT tm FROM TeamMember tm WHERE tm.team = t)")
    List<Long> findProjectIdsWithTeamMembers();

    Optional<Team> findByProjectId(Long projectId);
} 