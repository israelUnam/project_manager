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
            portfolio1.setName(
                    "4to. Congreso Internacional en Evaluación y Dirección Ética de Proyectos Públicos y Sociales ");
            portfolio1.setDescription("Congreso a realizarse en noviembre.");
            portfolio1.setObjectivos(
                    "Fomentar la creación de un espacio para promover, difundir, compartir reflexiones, concepciones y conocimientos, teóricos y aplicados, sobre la ética en la dirección de proyectos públicos y sociales; mediante la participación activa de especialistas, profesionales y de líderes de opinión que aporten y enriquezcan los conocimientos y la práctica del comportamiento ético en el mundo laboral de las personas que deseen desenvolverse dentro de la dirección de proyectos, y en las que ya se desenvuelven, en el ámbito de la dirección de proyectos públicos y sociales, en sus diferentes dominios*; para el óptimo aprovechamiento de los recursos y el máximo beneficio social. "
                            + "*Entiéndase como proyectos públicos y sociales, obra pública, campañas de impacto social, política pública, salud, mejora en la gestión y trámite gubernamental, tecnología aplicada, ciudadanía digital, innovación y desarrollo tecnológico en beneficio de la sociedad, etc.");
            portfolio1.setStartDate(LocalDateTime.of(2025, 6, 1, 0, 0));
            portfolio1.setEndDate(LocalDateTime.of(2025, 12, 31, 0, 0));
            portfolio1.setMisión(
                    "Promover entre los profesionales la adopción de una conducta ética en la dirección de los proyectos.");
            portfolio1.setVisión(
                    "Crear un entorno profesional en proyectos públicos y sociales que inspire confianza, transparencia, integridad y responsabilidad en todas las etapas de desarrollo, garantizando así un futuro de bienestar común y sostenible. ");
            portfolio1.setValores("Sabiduria, Justicia, Templanza, Coraje.");
            portfolio1.setCriteriosPriorizacion(
                    "Visibilidad Institucional, Viabilidad, tecnica, Impacto social, Riesgo operativo controlado.");
            portfolio1.setStatus(PortfolioStatus.ACTIVO);

            Metas meta1 = new Metas("Meta 1",
                    "Establecer 1 programa y 3 proyectos",
                    LocalDate.of(2025, 1, 1),
                    null,
                    MetasStatus.EN_PROGRESO,
                    true,
                    1.0,
                    10.0,
                    portfolio1);

            Metas meta2 = new Metas("Meta 2",
                    "Preparar el Acta del Programa, y 3 actas de constitución de proyectos",
                    LocalDate.of(2025, 1, 1),
                    null,
                    MetasStatus.PLANIFICADA,
                    true,
                    0.0,
                    10.0,
                    portfolio1);

            Metas meta3 = new Metas("Meta 3",
                    "Preparar la planificación del programa y de los 3 proyectos",
                    LocalDate.of(2025, 1, 1),
                    null,
                    MetasStatus.PLANIFICADA,
                    true,
                    0.0,
                    10.0,
                    portfolio1);

            Metas meta4 = new Metas("Meta 4",
                    "Preparar el entorno de arranque del congreso",
                    LocalDate.of(2025, 1, 1),
                    null,
                    MetasStatus.PLANIFICADA,
                    true,
                    0.0,
                    10.0,
                    portfolio1);

            Metas meta5 = new Metas("Meta 5",
                    "Ejecución del congreso",
                    LocalDate.of(2025, 1, 1),
                    null,
                    MetasStatus.PLANIFICADA,
                    true,
                    0.0,
                    10.0,
                    portfolio1);

            Metas meta6 = new Metas("Meta 6",
                    "Realizar las memorias congreso",
                    LocalDate.of(2025, 1, 1),
                    null,
                    MetasStatus.PLANIFICADA,
                    true,
                    0.0,
                    10.0,
                    portfolio1);

            portfolio1.setMetas(List.of(meta1, meta2, meta3, meta4, meta5, meta6));

            Portfolio portfolio2 = new Portfolio();
            portfolio2.setName("Diplomado de Administración de Proyectos - DAP27");
            portfolio2.setDescription("Portafolio del Diplomado de Administración de Proyectos.");
            portfolio2.setStartDate(LocalDateTime.of(2025, 5, 31, 0, 0));
            portfolio2.setEndDate(LocalDateTime.of(2025, 12, 13, 0, 0));
            portfolio2.setObjectivos(
                    "El participante desarrollará competencias en torno al estándar y prácticas internacionales basadas en la\r\n"
                            + //
                            "Guía de los Fundamentos PMBOK® en relación con la Administración de Proyectos mediante el estudio\r\n"
                            + //
                            "teórico y aplicación práctica a un proyecto real, desde su inicio, pasando por su ejecución hasta su cierre y\r\n"
                            + //
                            "entrega de reporte.");
            portfolio2.setMisión(
                    "Formar profesionales íntegros y altamente competentes en la dirección de proyectos, dotándolos de los conocimientos teóricos, las mejores prácticas y los estándares internacionales del Project Management Institute (PMI®). A través de una metodología eminentemente práctica y la aplicación a proyectos reales, capacitamos a los participantes para planificar, ejecutar y controlar proyectos de manera exitosa, contribuyendo así a la competitividad y el desarrollo de las organizaciones en un entorno global.");
            portfolio2.setVisión(
                    "Ser el programa de diplomado líder y de referencia en México en la enseñanza de la administración de proyectos bajo estándares internacionales, reconocido por la excelencia académica de sus egresados, su impacto positivo en la gestión de proyectos en diversos sectores y por mantenerse a la vanguardia en la difusión y aplicación de las prácticas más innovadoras y eficientes del PMBOK®. Aspiramos a construir una comunidad de profesionales que se distingan por su liderazgo, su ética y su capacidad para transformar ideas en resultados tangibles y exitosos");
            portfolio2.setValores(
                    "Nosotros, integrantes del diplomado, conscientes de nuestra responsabilidad como futuros\r\n" + //
                            "líderes y profesionales en la gestión de proyectos, adoptamos el presente código de ética\r\n"
                            + //
                            "como guía en nuestro actuar académico y profesional:\r\n" + //
                            "- Ejerceremos la sabiduría al tomar decisiones informadas, reflexivas y éticamente\r\n" + //
                            "responsables en el desarrollo de proyectos.\r\n" + //
                            "- Seremos responsables y disciplinados, cumpliendo con nuestras tareas, tiempos y\r\n" + //
                            "compromisos, demostrando profesionalismo en todo momento.\r\n" + //
                            "- Actuaremos con templanza, mostrando tolerancia ante la diversidad de ideas y\r\n" + //
                            "culturas, y humildad para aprender de nuestros errores y del conocimiento de otros.\r\n" + //
                            "- Demostraremos coraje ante los desafíos, desarrollando liderazgo positivo,\r\n" + //
                            "perseverancia en los objetivos y una visión estratégica orientada al impacto social y\r\n"
                            + //
                            "organizacional.\r\n" + //
                            "- Practicaremos la justicia, asegurando honestidad en nuestras acciones, respeto por los\r\n"
                            + //
                            "demás, comunicación efectiva y compromiso con la verdad.\r\n" + //
                            "- Rechazamos toda forma de deshonestidad académica, como el plagio o la manipulación\r\n" + //
                            "de información, y fomentamos un ambiente de confianza y colaboración.\r\n" + //
                            "- Promoveremos un entorno de aprendizaje inclusivo, respetuoso y ético, en el que todas\r\n"
                            + //
                            "las voces sean escuchadas y valoradas.\r\n" + //
                            "\r\n" + //
                            "\"Nuestros valores nos definen; nuestros actos los validan.\"\r\n" + //
                            "- Adaptación de Gandhi");
            portfolio2.setCriteriosPriorizacion(
                    "Desarrollo de competencias profesionales en Dirección de Proyectos, Aplicación práctica, Impacto en la socidedad, Cumplimiento de estándares internacionales, Innovación, Ética profesional y Mmejora continua.");
            portfolio2.setStatus(PortfolioStatus.ACTIVO);

            Metas metaB1 = new Metas("Meta 1",
                    "Etica",
                    LocalDate.of(2025, 5, 31),
                    LocalDate.of(2025, 6, 14),
                    MetasStatus.COMPLETADA,
                    true,
                    3.0,
                    3.0,
                    portfolio2);

            Metas metaB2 = new Metas("Meta 2",
                    "Introducción",
                    LocalDate.of(2025, 6, 21),
                    LocalDate.of(2025, 7, 05),
                    MetasStatus.EN_PROGRESO,
                    true,
                    1.0,
                    3.0,
                    portfolio2);

            Metas metaB3 = new Metas("Meta 3",
                    "Integración",
                    LocalDate.of(2025, 8, 02),
                    LocalDate.of(2025, 7, 16),
                    MetasStatus.PLANIFICADA,
                    true,
                    0.0,
                    3.0,
                    portfolio2);

            Metas metaB4 = new Metas("Meta 4",
                    "Restricciones y Planeación",
                    LocalDate.of(2025, 8, 23),
                    LocalDate.of(2025, 9, 06),
                    MetasStatus.PLANIFICADA,
                    true,
                    0.0,
                    3.0,
                    portfolio2);

            Metas metaB5 = new Metas("Meta 5",
                    "Ejecución",
                    LocalDate.of(2025, 9, 20),
                    LocalDate.of(2025, 10, 04),
                    MetasStatus.PLANIFICADA,
                    true,
                    0.0,
                    3.0,
                    portfolio2);

            Metas metaB6 = new Metas("Meta 6",
                    "Monitoreo",
                    LocalDate.of(2025, 10, 11),
                    LocalDate.of(2025, 10, 25),
                    MetasStatus.PLANIFICADA,
                    true,
                    0.0,
                    3.0,
                    portfolio2);

            Metas metaB7 = new Metas("Meta 7",
                    "Cierre",
                    LocalDate.of(2025, 11, 8),
                    LocalDate.of(2025, 11, 22),
                    MetasStatus.PLANIFICADA,
                    true,
                    0.0,
                    3.0,
                    portfolio2);    
                    
            Metas metaB8 = new Metas("Meta 8",
                    "Cierre",
                    LocalDate.of(2025, 11, 29),
                    LocalDate.of(2025, 12, 13),
                    MetasStatus.PLANIFICADA,
                    true,
                    0.0,
                    3.0,
                    portfolio2);        

            portfolio2.setMetas(List.of(metaB1, metaB2, metaB3, metaB4, metaB5, metaB6, metaB7, metaB8));

            portfolioRepository.save(portfolio1);
            portfolioRepository.save(portfolio2);

            Program program = new Program();
            program.setName("Prog. 1");
            program.setDescription("Reúne los proyectos que permitirán realizar el CODEP 2025");
            program.setStartDate(LocalDate.of(2025, 6, 1));
            program.setEndDate(LocalDate.of(2025, 12, 31));
            program.setStatus(ProgramStatus.ACTIVO);
            program.setCreatedAt(LocalDateTime.now());
            program.setUpdatedAt(LocalDateTime.now());
            program.setPortfolio(portfolio1);
            programRepository.save(program);

            Program program2 = new Program();
            program2.setName("Prog. 2");
            program2.setDescription("Gestión Institucional. Apoya las relaciones al interior de la UNAM para realizar el congreso");
            program2.setStartDate(LocalDate.of(2025, 6, 1));
            program2.setEndDate(LocalDate.of(2025, 12, 31));
            program2.setStatus(ProgramStatus.ACTIVO);
            program2.setCreatedAt(LocalDateTime.now());
            program2.setUpdatedAt(LocalDateTime.now());
            program2.setPortfolio(portfolio1);

            programRepository.save(program2);
        }
    }
}
