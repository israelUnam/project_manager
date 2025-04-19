# Sistema de Gestión OPM

Este proyecto Java Spring tiene como objetivo proporcionar una plataforma para que la organización gestione sus iniciativas bajo el modelo de Gestión de Portafolio, Programa y Proyecto (OPM). Permite la creación, seguimiento y gestión de portafolios, programas y proyectos, incluyendo la documentación clave asociada a estos últimos.

## Modelo de Datos

El sistema implementa el siguiente modelo de datos principal:

* **Portafolio:** Agrupación estratégica de programas.
* **Programa:** Grupo de proyectos relacionados gestionados de manera coordinada para obtener beneficios que no se lograrían gestionándolos individualmente.
* **Proyecto:** Esfuerzo temporal emprendido para crear un producto, servicio o resultado único.

Dentro de cada **Proyecto**, se gestionarán los siguientes documentos e información:

* **Acta de Constitución del Proyecto:** Documento que formaliza el proyecto y autoriza al director del proyecto a utilizar los recursos de la organización.
* **Plan para la Dirección del Proyecto:** Documento que define cómo se ejecutará, supervisará y controlará el proyecto.
* **Cronograma del Proyecto:** Representación gráfica del plan del proyecto, mostrando las actividades, duraciones y dependencias.
* **Registro de Riesgos:** Documento donde se identifican, analizan y planifican las respuestas a los riesgos del proyecto.
* *(Se podrán añadir más documentos y funcionalidades en el futuro)*

## Funcionalidades Principales (Planificadas)

* **Gestión de Portafolios:**
    * Creación, visualización y edición de portafolios.
    * Asignación de programas y proyectos a portafolios.
    * Visualización del estado general de los portafolios.
    * Gestiòn de metas organizacionales
* **Gestión de Programas:**
    * Creación, visualización y edición de programas.
    * Asignación de proyectos a programas.
    * Visualización del estado general de los programas.
* **Gestión de Proyectos:**
    * Creación, visualización y edición de proyectos.
    * Carga y gestión de documentos asociados (Acta de Constitución, Plan de Desarrollo, Cronograma, Registro de Riesgos, etc.).
    * Seguimiento del estado del proyecto.
    * *(Se podrán añadir funcionalidades como gestión de issues, lecciones aprendidas, etc.)*

## Tecnologías Utilizadas

* **Java:** Lenguaje de programación principal.
* **Spring Framework:** Framework de desarrollo empresarial para Java (Spring Boot, Spring Data JPA, Spring Web).
* **Base de Datos:** MySQL, H2 para testing
* **Maven:** Herramienta de gestión de dependencias y construcción.
* **Thymeleaf** Motor de plantillas HTML5

## Requisitos del Sistema

* JDK (Java Development Kit) 17 o superior.
* Maven instalado.
* MySQL para produción

## Configuración

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/israelUnam/project_manager.git
    ```

2.  **Configurar la base de datos:**
    * Modificar el archivo de configuración de Spring `application.properties` con los detalles de conexión a la base de datos.
    ```properties
    spring.datasource.url=jdbc:<tipo_de_base_de_datos>://<host>:<puerto>/<nombre_de_la_base_de_datos>
    spring.datasource.username=<usuario>
    spring.datasource.password=<contraseña>
    spring.jpa.hibernate.ddl-auto=update
    ```
    *(Ajustar según la base de datos utilizada)*

3.  **Construir el proyecto:**
    * Con Maven:
        ```bash
        mvn clean install

## Ejecución

1.  **Ejecutar la aplicación:**
    * Con Maven:
        ```bash
        mvn spring-boot:run
        ```

2.  **Acceder a la aplicación:**
    * La aplicación estará disponible en `http://localhost:8020` (o el puerto configurado).

## Próximos Pasos (Roadmap)

* Implementación de la gestión básica de Portafolios, Programas y Proyectos.
* Desarrollo de la funcionalidad para cargar y gestionar el Acta de Constitución del Proyecto.
* Implementación de la gestión del Plan para la Dirección del Proyecto.
* Desarrollo de la funcionalidad para crear y visualizar el Cronograma del Proyecto.
* Implementación de la gestión del Registro de Riesgos.
* Creación de interfaces de usuario para todas las funcionalidades.
* Implementación de autenticación y autorización.

## Contribución

Las contribuciones son bienvenidas. Por favor, revisa las [Guías de Contribución](CONTRIBUTING.md) para obtener más detalles.

## Licencia

Este proyecto está bajo la licencia [MIT](LICENSE) (o la licencia que se decida utilizar).

## Contacto

israeloc - iortega@unam.mx
