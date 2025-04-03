package com.losung.projectmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.losung.projectmanager.dto.PortfolioEstadistica;
import com.losung.projectmanager.model.Metas;
import com.losung.projectmanager.model.MetasStatus;
import com.losung.projectmanager.model.Portfolio;
import com.losung.projectmanager.model.PortfolioStatus;
import com.losung.projectmanager.repository.MetasRepository;
import com.losung.projectmanager.repository.PortfolioRepository;


@SpringBootTest
public class PortfolioServiceTest {

    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private MetasRepository metasRepository;

    @InjectMocks
    private PortfolioService portfolioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void calcularEstadisticasActivos_ShouldReturnCorrectStatistics() {
        System.out.println("Test: calcularEstadisticasActivos_ShouldReturnCorrectStatistics");
        // Arrange
        Portfolio portfolio1 = new Portfolio();
        portfolio1.setId(1L);
        portfolio1.setName("Portfolio 1");
        portfolio1.setStatus(PortfolioStatus.ACTIVO);

        Portfolio portfolio2 = new Portfolio();
        portfolio2.setId(2L);
        portfolio2.setName("Portfolio 2");
        portfolio2.setStatus(PortfolioStatus.ACTIVO);

        List<Portfolio> portfoliosActivos = Arrays.asList(portfolio1, portfolio2);
        when(portfolioRepository.findByStatus(PortfolioStatus.ACTIVO)).thenReturn(portfoliosActivos);

        // Metas para Portfolio 1
        Metas meta1 = new Metas();
        meta1.setValorActual(50.0);
        meta1.setLineaBase(100.0);
        meta1.setStatus(MetasStatus.EN_PROGRESO);

        Metas meta2 = new Metas();
        meta2.setValorActual(75.0);
        meta2.setLineaBase(100.0);
        meta2.setStatus(MetasStatus.COMPLETADA);

        List<Metas> metasPortfolio1 = Arrays.asList(meta1, meta2);
        when(metasRepository.findByPortfolioIdAndStatusIn(
            eq(1L), 
            eq(Arrays.asList(MetasStatus.EN_PROGRESO, MetasStatus.COMPLETADA))
        )).thenReturn(metasPortfolio1);

        // Metas para Portfolio 2
        Metas meta3 = new Metas();
        meta3.setValorActual(25.0);
        meta3.setLineaBase(50.0);
        meta3.setStatus(MetasStatus.EN_PROGRESO);

        List<Metas> metasPortfolio2 = Arrays.asList(meta3);
        when(metasRepository.findByPortfolioIdAndStatusIn(
            eq(2L), 
            eq(Arrays.asList(MetasStatus.EN_PROGRESO, MetasStatus.COMPLETADA))
        )).thenReturn(metasPortfolio2);

        // Act
        List<PortfolioEstadistica> estadisticas = portfolioService.calcularEstadisticasActivos();

        // Assert
        assertNotNull(estadisticas);
        assertEquals(2, estadisticas.size());

        // Verificar estadísticas del Portfolio 1
        PortfolioEstadistica estadistica1 = estadisticas.get(0);
        assertEquals(1L, estadistica1.getId());
        assertEquals("Portfolio 1", estadistica1.getName());
        assertEquals(2, estadistica1.getTotalMetas());
        assertEquals(200, estadistica1.getTotalLineaBase());
        assertEquals(125, estadistica1.getTotalValorActual());
        assertEquals(0.625, estadistica1.getPorcentajeAvance());

        // Verificar estadísticas del Portfolio 2
        PortfolioEstadistica estadistica2 = estadisticas.get(1);
        assertEquals(2L, estadistica2.getId());
        assertEquals("Portfolio 2", estadistica2.getName());
        assertEquals(1, estadistica2.getTotalMetas());
        assertEquals(50, estadistica2.getTotalLineaBase());
        assertEquals(25, estadistica2.getTotalValorActual());
        assertEquals(0.5, estadistica2.getPorcentajeAvance());

        // Verify
        verify(portfolioRepository).findByStatus(PortfolioStatus.ACTIVO);
        verify(metasRepository, times(2)).findByPortfolioIdAndStatusIn(
            any(), 
            eq(Arrays.asList(MetasStatus.EN_PROGRESO, MetasStatus.COMPLETADA))
        );
        
        System.out.println(estadistica1);
        System.out.println(estadistica2);
    }
} 