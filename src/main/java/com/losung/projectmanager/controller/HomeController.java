package com.losung.projectmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Project Manager");
        model.addAttribute("message", "Bienvenido al Sistema de Gestión de Proyectos");
        return "home";
    }
} 