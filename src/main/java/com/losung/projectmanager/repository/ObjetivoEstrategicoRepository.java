package com.losung.projectmanager.repository;

import com.losung.projectmanager.model.ObjetivoEstrategico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ObjetivoEstrategicoRepository extends JpaRepository<ObjetivoEstrategico, Long> {
    
    // Buscar objetivos por nombre (búsqueda parcial)
    List<ObjetivoEstrategico> findByNombreContainingIgnoreCase(String nombre);
    
    
    // Buscar objetivos por rango de fechas
    List<ObjetivoEstrategico> findByFechaInicioBetweenOrFechaFinBetween(
        LocalDateTime fechaInicio1, LocalDateTime fechaFin1,
        LocalDateTime fechaInicio2, LocalDateTime fechaFin2
    );
    
    // Buscar objetivos por indicador de rendimiento
    List<ObjetivoEstrategico> findByIndicadorRendimientoContainingIgnoreCase(String indicador);
    
    // Verificar si existe un objetivo con el mismo nombre
    boolean existsByNombre(String nombre);
} 