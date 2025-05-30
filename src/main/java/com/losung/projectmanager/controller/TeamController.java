package com.losung.projectmanager.controller;

import com.losung.projectmanager.model.Team;
import com.losung.projectmanager.model.TeamMember;
import com.losung.projectmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;

@Controller
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public String listTeams(Model model) {
        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        model.addAttribute("availableProjects", teamService.findProjectsWithoutTeam());
        
        // Agrupar miembros por equipo
        Map<Long, List<TeamMember>> membersByTeam = teams.stream()
            .collect(Collectors.toMap(
                Team::getId,
                team -> teamService.findTeamMembers(team.getId())
            ));
        model.addAttribute("membersByTeam", membersByTeam);
        
        return "team/list";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Team getTeam(@PathVariable Long id) {
        return teamService.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }

    @PostMapping
    public String createTeam(@ModelAttribute Team team) {
        teamService.save(team);
        return "redirect:/teams";
    }

    @PostMapping("/{id}/update")
    public String updateTeam(@PathVariable Long id, @ModelAttribute Team team) {
        team.setId(id);
        teamService.save(team);
        return "redirect:/teams";
    }

    @PostMapping("/{id}/delete")
    public String deleteTeam(@PathVariable Long id) {
        teamService.deleteById(id);
        return "redirect:/teams";
    }

    @PostMapping("/{teamId}/members")
    public String addTeamMember(@PathVariable Long teamId, @ModelAttribute TeamMember member) {
        member.setTeam(teamService.findById(teamId).orElseThrow());
        teamService.saveTeamMember(member);
        return "redirect:/teams";
    }

    @PostMapping("/members/{id}/update")
    public String updateTeamMember(@PathVariable Long id, @ModelAttribute TeamMember member) {
        member.setId(id);
        teamService.saveTeamMember(member);
        return "redirect:/teams";
    }

    @PostMapping("/members/{id}/delete")
    public String deleteTeamMember(@PathVariable Long id) {
        try {
            teamService.deleteTeamMember(id);
            return "redirect:/teams";
        } catch (RuntimeException e) {
            // Redirigir con mensaje de error
            return "redirect:/teams?error=" + e.getMessage();
        }
    }

    @GetMapping("/members/{id}")
    @ResponseBody
    public TeamMember getTeamMember(@PathVariable Long id) {
        return teamService.findTeamMemberById(id)
                .orElseThrow(() -> new RuntimeException("Integrante no encontrado"));
    }

    @GetMapping("/members/{id}/check-tasks")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkMemberTasks(@PathVariable Long id) {
        boolean hasTasks = teamService.hasAssignedTasks(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("hasTasks", hasTasks);
        return ResponseEntity.ok(response);
    }
} 