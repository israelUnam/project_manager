package com.losung.projectmanager.dto;


import lombok.Data;

@Data
public class PortfolioEstadistica {
    private Long id;
    private String name;
    private Integer totalLineaBase;
    private Double porcentajeAvance;
    private Integer totalValorActual;
    private Integer totalMetas;
}
