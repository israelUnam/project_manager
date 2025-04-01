package com.losung.projectmanager.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MetaDTO {
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String status;
} 