package com.losung.projectmanager.repository;

import com.losung.projectmanager.model.Metas;
import com.losung.projectmanager.model.MetasStatus;
import com.losung.projectmanager.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MetasRepository extends JpaRepository<Metas, Long> {
    
    // Buscar metas por nombre (búsqueda parcial)
    List<Metas> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar metas por estado
    List<Metas> findByStatus(MetasStatus status);
    
    // Buscar metas por portfolio
    List<Metas> findByPortfolio(Portfolio portfolio);
    
    // Buscar metas por portfolio y estado
    List<Metas> findByPortfolioAndStatus(Portfolio portfolio, MetasStatus status);
    
    // Buscar metas activas ordenadas por fecha de inicio
    List<Metas> findByStatusOrderByFechaInicioDesc(MetasStatus status);
    
    // Buscar metas dentro de un rango de fechas
    List<Metas> findByFechaInicioBetweenOrFechaFinBetween(
        LocalDateTime fechaInicio1, LocalDateTime fechaFin1,
        LocalDateTime fechaInicio2, LocalDateTime fechaFin2
    );
    
    // Verificar si existe una meta con el mismo nombre en un portfolio
    boolean existsByNombreAndPortfolio(String nombre, Portfolio portfolio);
} 