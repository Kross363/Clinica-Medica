
# API REST para Clínica Médica

Este proyecto es una API REST desarrollada en **Java 17** con **Spring Boot 3.3.5**, diseñada para gestionar las operaciones de una clínica médica. Incluye funcionalidades CRUD, autenticación y autorización, validaciones de reglas de negocio, manejo de errores y documentación de la API para facilitar su uso.

## Tecnologías y dependencias

- **Java 17**
- **Spring Boot 3.3.5**
  - `spring-boot-starter-web`: Para manejar peticiones HTTP.
  - `spring-boot-devtools`: Para facilitar el desarrollo.
  - `spring-boot-starter-data-jpa`: Para persistencia de datos.
  - `spring-boot-starter-validation`: Para validaciones.
  - `spring-boot-starter-security`: Para seguridad.
  - `spring-boot-starter-test` y `spring-security-test`: Para pruebas.
- **Lombok**: Para reducir el boilerplate.
- **MySQL/Flyway**: Gestión de base de datos y migraciones.
- **JWT (java-jwt 4.4.0)**: Manejo de autenticación y autorización.
- **Springdoc OpenAPI (2.5.0)**: Para documentar la API.
- **Maven**: Para la gestión de dependencias.

## Funcionalidades principales

1. **Creación de API REST** con endpoints CRUD para recursos como médicos, pacientes y consultas.
2. **Validaciones de negocio**:
   - Horarios de atención: Lunes a sábado, de 07:00 a 19:00.
   - Duración fija de consultas: 1 hora.
   - Consultas deben programarse con 30 minutos de anticipación.
   - Restricciones de inactividad para médicos y pacientes.
   - Prohibición de múltiples consultas para un paciente el mismo día.
   - Validación de disponibilidad de médicos para consultas.
   - Asignación automática de médicos disponibles si no se especifica uno.
3. **Paginación y ordenamiento** para listar médicos y pacientes.
4. **Manejo de errores** con respuestas claras y estructuradas.
5. **Autenticación y autorización** mediante JWT.
6. **Documentación de la API** accesible a través de una URL gracias a Springdoc.
7. **Requests HTTP** compatibles con herramientas como Insomnia.

## Reglas de negocio de la clínica médica

- **Horario de atención**: Lunes a sábado, 07:00-19:00.
- **Duración de consultas**: 1 hora fija.
- **Reservas**:
  - Deben realizarse con 30 minutos de anticipación.
  - Prohibido reservar con médicos o pacientes inactivos.
  - No se permite más de una consulta diaria por paciente.
  - Validación de disponibilidad del médico en el mismo día/hora.
  - Médico puede asignarse automáticamente si no se elige uno.

## Requisitos para ejecutar el proyecto

1. **Java 17** instalado.
2. **MySQL** configurado.
3. Configuración de la base de datos en el archivo `application.properties` o `application.yml`.
4. **Maven** para gestionar las dependencias.

## Uso de la API

- **Documentación**: Accede a la documentación interactiva generada automáticamente en `/swagger-ui.html` (o la URL correspondiente configurada en tu entorno).
- **Requests HTTP**: Realiza pruebas mediante herramientas como Insomnia.

## Instalación y ejecución

1. Clona el repositorio.
2. Configura tu base de datos MySQL.
3. Ejecuta las migraciones con **Flyway**.
4. Usa Maven para compilar y ejecutar:
   ```bash
   mvn spring-boot:run
5. Accede a los endpoints según se documenta en Springdoc.


