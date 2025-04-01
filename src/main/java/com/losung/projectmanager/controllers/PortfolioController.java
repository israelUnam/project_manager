package com.losung.projectmanager.controllers;

import com.losung.projectmanager.model.Portfolio;
import com.losung.projectmanager.model.PortfolioStatus;
import com.losung.projectmanager.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.util.List;

@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @GetMapping
    public String listPortfolios(Model model) {
        List<Portfolio> portfolios = portfolioRepository.findAll();
        model.addAttribute("portfolios", portfolios);
        return "portfolio/list";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Portfolio getPortfolio(@PathVariable Long id) {
        return portfolioRepository.findById(id)
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
        
        Portfolio portfolio = new Portfolio();
        portfolio.setName(name);
        portfolio.setDescription(description);
        portfolio.setStartDate(LocalDateTime.parse(startDate + "T00:00:00"));
        portfolio.setEndDate(LocalDateTime.parse(endDate + "T00:00:00"));
        portfolio.setStatus(PortfolioStatus.valueOf(status));
        portfolio.setMisión(misión);
        portfolio.setVisión(visión);
        portfolio.setValores(valores);
        portfolio.setCriteriosPriorizacion(criteriosPriorizacion);
        
        portfolioRepository.save(portfolio);
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
        
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portafolio no encontrado"));
        
        portfolio.setName(name);
        portfolio.setDescription(description);
        portfolio.setStartDate(LocalDateTime.parse(startDate + "T00:00:00"));
        portfolio.setEndDate(LocalDateTime.parse(endDate + "T00:00:00"));
        portfolio.setStatus(PortfolioStatus.valueOf(status));
        portfolio.setMisión(misión);
        portfolio.setVisión(visión);
        portfolio.setValores(valores);
        portfolio.setCriteriosPriorizacion(criteriosPriorizacion);
        
        portfolioRepository.save(portfolio);
        return "redirect:/portfolios";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deletePortfolio(@PathVariable Long id) {
        if (portfolioRepository.existsById(id)) {
            portfolioRepository.deleteById(id);
        }
        return "redirect:/portfolios";
    }
} 