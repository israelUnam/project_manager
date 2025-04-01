package com.losung.projectmanager.repository;

import com.losung.projectmanager.model.Program;
import com.losung.projectmanager.model.ProgramStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    
    // Buscar programas por nombre (búsqueda parcial)
    List<Program> findByNameContainingIgnoreCase(String name);
    
    // Buscar programas por estado
    List<Program> findByStatus(ProgramStatus status);
    
    // Buscar programas activos
    List<Program> findByStatusOrderByStartDateDesc(ProgramStatus status);
    
    // Buscar programas por rango de fechas
    List<Program> findByStartDateBetweenOrEndDateBetween(
        LocalDateTime startDate1, LocalDateTime endDate1,
        LocalDateTime startDate2, LocalDateTime endDate2
    );
    
    // Verificar si existe un programa con el mismo nombre
    boolean existsByName(String name);
} 