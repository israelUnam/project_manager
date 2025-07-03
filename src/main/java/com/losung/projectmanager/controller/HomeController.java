package com.losung.projectmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import com.losung.projectmanager.repository.PortfolioRepository;
import com.losung.projectmanager.repository.ProgramRepository;
import com.losung.projectmanager.model.PortfolioStatus;
import com.losung.projectmanager.model.ProgramStatus;
import com.losung.projectmanager.service.BitacoraService;
import com.losung.projectmanager.model.Bitacora;

import java.util.List;

@Controller
public class HomeController {
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private BitacoraService bitacoraService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Project Manager");
        model.addAttribute("message", "Bienvenido al Sistema de Gestión de Proyectos");
        int portafoliosActivos = portfolioRepository.findByStatus(PortfolioStatus.ACTIVO).size();
        int programasActivos = programRepository.findByStatus(ProgramStatus.ACTIVO).size();
        model.addAttribute("portafoliosActivos", portafoliosActivos);
        model.addAttribute("programasActivos", programasActivos);

        List<Bitacora> accionesRecientes = bitacoraService.findLastN(10);
        model.addAttribute("AccionesRecientes", accionesRecientes);

        return "home";
    }
}