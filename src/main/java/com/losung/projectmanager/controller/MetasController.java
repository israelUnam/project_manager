package com.losung.projectmanager.controller;

import com.losung.projectmanager.model.Metas;
import com.losung.projectmanager.model.Portfolio;
import com.losung.projectmanager.repository.MetasRepository;
import com.losung.projectmanager.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/portfolios")
public class MetasController {

    @Autowired
    private MetasRepository metasRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @GetMapping("/{portfolioId}/metas")
    @ResponseBody
    public List<Metas> getMetas(@PathVariable Long portfolioId) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio no encontrado"));
        return metasRepository.findByPortfolio(portfolio);
    }

    @GetMapping("/{portfolioId}/metas/{metaId}")
    @ResponseBody
    public Metas getMeta(@PathVariable Long portfolioId, @PathVariable Long metaId) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio no encontrado"));
        return metasRepository.findById(metaId)
                .filter(meta -> meta.getPortfolio().getId().equals(portfolioId))
                .orElseThrow(() -> new RuntimeException("Meta no encontrada"));
    }

    @PostMapping("/{portfolioId}/metas")
    @ResponseBody
    public Metas createMeta(@PathVariable Long portfolioId, @RequestBody Metas meta) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio no encontrado"));
        meta.setPortfolio(portfolio);
        return metasRepository.save(meta);
    }

    @PutMapping("/{portfolioId}/metas/{metaId}")
    @ResponseBody
    public Metas updateMeta(@PathVariable Long portfolioId, @PathVariable Long metaId, @RequestBody Metas meta) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio no encontrado"));
        
        Metas existingMeta = metasRepository.findById(metaId)
                .filter(m -> m.getPortfolio().getId().equals(portfolioId))
                .orElseThrow(() -> new RuntimeException("Meta no encontrada"));

        existingMeta.setNombre(meta.getNombre());
        existingMeta.setDescripcion(meta.getDescripcion());
        existingMeta.setFechaInicio(meta.getFechaInicio());
        existingMeta.setFechaFin(meta.getFechaFin());
        existingMeta.setStatus(meta.getStatus());
        existingMeta.setIndicador(meta.getIndicador());
        existingMeta.setValorActual(meta.getValorActual());
        existingMeta.setLineaBase(meta.getLineaBase());

        return metasRepository.save(existingMeta);
    }

    @DeleteMapping("/{portfolioId}/metas/{metaId}")
    @ResponseBody
    public ResponseEntity<?> deleteMeta(@PathVariable Long portfolioId, @PathVariable Long metaId) {
        Metas meta = metasRepository.findById(metaId)
                .filter(m -> m.getPortfolio().getId().equals(portfolioId))
                .orElseThrow(() -> new RuntimeException("Meta no encontrada"));
        
        metasRepository.delete(meta);
        return ResponseEntity.ok().build();
    }
} 