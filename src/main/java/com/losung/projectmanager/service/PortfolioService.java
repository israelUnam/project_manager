package com.losung.projectmanager.service;

import com.losung.projectmanager.model.Portfolio;
import com.losung.projectmanager.model.PortfolioStatus;
import com.losung.projectmanager.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    public List<Portfolio> findAll() {
        return portfolioRepository.findAll();
    }

    public Optional<Portfolio> findById(Long id) {
        return portfolioRepository.findById(id);
    }

    @Transactional
    public Portfolio createPortfolio(
            String name,
            String description,
            String startDate,
            String endDate,
            String status,
            String misión,
            String visión,
            String valores,
            String criteriosPriorizacion) {
        
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
        
        return portfolioRepository.save(portfolio);
    }

    @Transactional
    public Portfolio updatePortfolio(
            Long id,
            String name,
            String description,
            String startDate,
            String endDate,
            String status,
            String misión,
            String visión,
            String valores,
            String criteriosPriorizacion) {
        
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
        
        return portfolioRepository.save(portfolio);
    }

    @Transactional
    public void deletePortfolio(Long id) {
        if (portfolioRepository.existsById(id)) {
            portfolioRepository.deleteById(id);
        }
    }

    public boolean existsById(Long id) {
        return portfolioRepository.existsById(id);
    }
} 