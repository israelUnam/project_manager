package com.losung.projectmanager.repository;

import com.losung.projectmanager.model.Portfolio;
import com.losung.projectmanager.model.PortfolioStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    
    // Buscar portafolios por nombre (búsqueda parcial)
    List<Portfolio> findByNameContainingIgnoreCase(String name);
    
    // Buscar portafolios por estado
    List<Portfolio> findByStatus(PortfolioStatus status);
    
    // Buscar portafolios activos ordenados por fecha de inicio
    List<Portfolio> findByStatusOrderByStartDateDesc(PortfolioStatus status);
    
    // Buscar portafolios por rango de fechas
    List<Portfolio> findByStartDateBetweenOrEndDateBetween(
        LocalDateTime startDate1, LocalDateTime endDate1,
        LocalDateTime startDate2, LocalDateTime endDate2
    );
    
    // Buscar portafolios por programa
    List<Portfolio> findByProgramId(Long programId);
    
    // Verificar si existe un portafolio con el mismo nombre
    boolean existsByName(String name);
} 