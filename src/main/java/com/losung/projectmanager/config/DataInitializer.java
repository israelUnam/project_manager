package com.losung.projectmanager.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.losung.projectmanager.model.Metas;
import com.losung.projectmanager.model.MetasStatus;
import com.losung.projectmanager.model.Portfolio;
import com.losung.projectmanager.model.PortfolioStatus;
import com.losung.projectmanager.model.Program;
import com.losung.projectmanager.model.ProgramStatus;
import com.losung.projectmanager.repository.PortfolioRepository;
import com.losung.projectmanager.repository.ProgramRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class DataInitializer {
    private PortfolioRepository portfolioRepository;
    private ProgramRepository programRepository;

    public DataInitializer(PortfolioRepository portfolioRepository, ProgramRepository programRepository) {
        this.portfolioRepository = portfolioRepository;
        this.programRepository = programRepository;
    }

    @PostConstruct
    public void init() {
        createSampleData();
    }

    private void createSampleData() {
        if (portfolioRepository.count() == 0) {
            Portfolio portfolio1 = new Portfolio();
            portfolio1.setName("Portafolio de Innovación");
            portfolio1.setDescription("Portafolio enfocado en proyectos de innovación tecnológica.");
            portfolio1.setStartDate(LocalDateTime.now().minusMonths(6));
            portfolio1.setEndDate(LocalDateTime.now().plusMonths(6));
            portfolio1.setMisión("Fomentar la innovación en la organización.");
            portfolio1.setVisión("Ser líderes en innovación tecnológica.");
            portfolio1.setValores("Innovación, Colaboración, Excelencia.");
            portfolio1.setCriteriosPriorizacion("Impacto, Viabilidad, Coste.");
            portfolio1.setStatus(PortfolioStatus.ACTIVO);

            Metas meta1 = new Metas("",
                    "Reducir el tiempo de desarrollo de productos en un 20%.",
                    LocalDate.of(2025, 1, 1),
                    null,
                    MetasStatus.EN_PROGRESO,
                    true,
                    10.0,
                    100.0,
                    portfolio1);

            Metas meta2 = new Metas("",
                    "Implementar al menos 3 nuevas tecnologías innovadoras.",
                    LocalDate.of(2025, 1, 1),
                    null,
                    MetasStatus.EN_PROGRESO,
                    true,
                    33.0,
                    100.0,
                    portfolio1);

            portfolio1.setMetas(List.of(meta1, meta2));

            Portfolio portfolio2 = new Portfolio();
            portfolio2.setName("Portafolio de Sostenibilidad");
            portfolio2.setDescription("Portafolio dedicado a proyectos de sostenibilidad ambiental.");
            portfolio2.setStartDate(LocalDateTime.now().minusMonths(3));
            portfolio2.setEndDate(LocalDateTime.now().plusMonths(9));
            portfolio2.setMisión("Promover prácticas sostenibles.");
            portfolio2.setVisión("Ser un referente en sostenibilidad.");
            portfolio2.setValores("Sostenibilidad, Responsabilidad, Innovación.");
            portfolio2.setCriteriosPriorizacion("Impacto ambiental, Coste, Escalabilidad.");
            portfolio2.setStatus(PortfolioStatus.ACTIVO);

            portfolioRepository.save(portfolio1);
            portfolioRepository.save(portfolio2);

            Program program = new Program();
            program.setName("Program 1");
            program.setDescription("Description of Program 1");
            program.setStartDate(LocalDateTime.now().minusMonths(3).toLocalDate());
            program.setEndDate(LocalDateTime.now().plusMonths(6).toLocalDate());
            program.setStatus(ProgramStatus.ACTIVO);
            program.setCreatedAt(LocalDateTime.now());
            program.setUpdatedAt(LocalDateTime.now());
            program.setPortfolio(portfolio1);

            programRepository.save(program);
        }
    }
}
