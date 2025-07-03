package com.losung.projectmanager.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.losung.projectmanager.dto.PortfolioEstadistica;
import com.losung.projectmanager.model.BitAcciones;
import com.losung.projectmanager.model.Bitacora;
import com.losung.projectmanager.model.Portfolio;
import com.losung.projectmanager.service.BitacoraService;
import com.losung.projectmanager.service.PortfolioService;

@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    private PortfolioService portfolioService;
    private BitacoraService bitacoraService;

    public PortfolioController(PortfolioService portfolioService, BitacoraService bitacoraService) {
        this.portfolioService = portfolioService;
        this.bitacoraService = bitacoraService;
    }

    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

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
            @RequestParam(required = false) String objectivos,
            @RequestParam(required = false) String misión,
            @RequestParam(required = false) String visión,
            @RequestParam(required = false) String valores,
            @RequestParam(required = false) String criteriosPriorizacion) {

        portfolioService.createPortfolio(
                name, description, startDate, endDate, status,
                objectivos, misión, visión, valores, criteriosPriorizacion);

        bitacoraService.save(new Bitacora(
                BitAcciones.PORTAFOLIO_CREADO, "usuario", LocalDateTime.now(), name));

        return "redirect:/portfolios";
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<?> updatePortfolio(
            @PathVariable Long id,
            @RequestBody Portfolio portfolio) {
        logger.info("[UPDATE] Llamada recibida para actualizar portafolio con id: {}", id);
        logger.info("[UPDATE] Datos recibidos: {}", portfolio);
        portfolio.setId(id);
        portfolioService.updatePortfolio(portfolio);
        logger.info("[UPDATE] Portafolio actualizado correctamente");

        bitacoraService.save(new Bitacora(
                BitAcciones.PORTAFOLIO_MODIFICADO, "usuario", LocalDateTime.now(), portfolio.getName()));

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.DELETE, RequestMethod.POST })
    public String deletePortfolio(@PathVariable Long id) {
        Portfolio portfolio = portfolioService.findById(id)
                .orElseThrow(() -> new RuntimeException("Portafolio no encontrado"));

        portfolioService.deletePortfolio(id);

        bitacoraService.save(new Bitacora(
                BitAcciones.PORTAFOLIO_BORRADO, "usuario", LocalDateTime.now(), portfolio.getName()));

        return "redirect:/portfolios";
    }
}