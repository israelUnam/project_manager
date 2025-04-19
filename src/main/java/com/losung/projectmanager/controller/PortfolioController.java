package com.losung.projectmanager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.losung.projectmanager.dto.PortfolioEstadistica;
import com.losung.projectmanager.model.Portfolio;

import com.losung.projectmanager.service.PortfolioService;

@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping
    public String listPortfolios(Model model) {
        List<Portfolio> portfolios = portfolioService.findAll();
        model.addAttribute("portfolios", portfolios);

        List<PortfolioEstadistica> estadisticas = portfolioService.calcularEstadisticasActivos();
              model.addAttribute("estadisticas", estadisticas);

        return "portfolio/list";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Portfolio getPortfolio(@PathVariable Long id) {
        return portfolioService.findById(id)
                .orElseThrow(() -> new RuntimeException("Portafolio no encontrado"));
    }

    @PostMapping
    public String createPortfolio(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String status,
            @RequestParam(required = false) String misión,
            @RequestParam(required = false) String visión,
            @RequestParam(required = false) String valores,
            @RequestParam(required = false) String criteriosPriorizacion) {
        
        portfolioService.createPortfolio(
            name, description, startDate, endDate, status,
            misión, visión, valores, criteriosPriorizacion
        );
        return "redirect:/portfolios";
    }

    @PostMapping("/{id}/update")
    public String updatePortfolio(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String status,
            @RequestParam(required = false) String misión,
            @RequestParam(required = false) String visión,
            @RequestParam(required = false) String valores,
            @RequestParam(required = false) String criteriosPriorizacion) {
        
        portfolioService.updatePortfolio(
            id, name, description, startDate, endDate, status,
            misión, visión, valores, criteriosPriorizacion
        );
        return "redirect:/portfolios";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deletePortfolio(@PathVariable Long id) {
        portfolioService.deletePortfolio(id);
        return "redirect:/portfolios";
    }
} 