package com.losung.projectmanager.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.losung.projectmanager.dto.PortfolioEstadistica;
import com.losung.projectmanager.model.Metas;
import com.losung.projectmanager.model.MetasStatus;
import com.losung.projectmanager.model.Portfolio;
import com.losung.projectmanager.model.PortfolioStatus;
import com.losung.projectmanager.repository.MetasRepository;
import com.losung.projectmanager.repository.PortfolioRepository;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private MetasRepository metasRepository;

    public List<Portfolio> findAll() {
        return portfolioRepository.findAll();
    }

    public Optional<Portfolio> findById(Long id) {
        return portfolioRepository.findById(id);
    }

    public List<PortfolioEstadistica> calcularEstadisticasActivos() {
        List<PortfolioEstadistica> estadistica = new ArrayList<>();

        List<Portfolio> portfolio = portfolioRepository.findByStatus(PortfolioStatus.ACTIVO);

        for (Portfolio p : portfolio) {
            PortfolioEstadistica e = new PortfolioEstadistica();
            e.setId(p.getId());
            e.setName(p.getName());
            
            List<MetasStatus> metasStatus = Arrays.asList(MetasStatus.EN_PROGRESO, MetasStatus.COMPLETADA);
            List<Metas> metas = metasRepository.findByPortfolioIdAndStatusIn(p.getId(), metasStatus);
            e.setTotalMetas(metas.size());  
            e.setTotalLineaBase((int) metas.stream().mapToDouble(Metas::getLineaBase).sum());
            e.setPorcentajeAvance(metas.stream().mapToDouble(Metas::getValorActual).sum() / e.getTotalLineaBase());
            e.setTotalValorActual((int) metas.stream().mapToDouble(Metas::getValorActual).sum());
            estadistica.add(e);
        }



        return estadistica;
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