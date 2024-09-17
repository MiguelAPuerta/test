# Sistema de Tiquetes Aéreos - SITAS - Proyecto B

## Descripción General

SITAS es una aplicación web desarrollada para gestionar los tiquetes de la aerolínea Singapur Airlines. El objetivo principal es proporcionar una experiencia eficiente a los usuarios, permitiendo la búsqueda de vuelos, la gestión de reservas, el manejo de equipaje, la selección de asientos, el procesamiento de pagos y la realización de check-in.

Este sistema está diseñado para ser escalable, flexible, seguro y mantenible, colaborando con diferentes actores para ofrecer una amplia gama de opciones a los usuarios.

Actores Principales
- Usuario Registrado: Usuario con cuenta en la plataforma.
- Usuario No Registrado: Visitante anónimo que aún no ha creado una cuenta.
- Administrador de la Aerolínea: Usuario con acceso a funciones avanzadas de gestión y monitoreo.

## Estructura del Proyecto

El proyecto SITAS se divide en varios módulos, cada uno con su propia rama en el repositorio:

1. **Autenticación y Autorización** (feature/auth)
   - **Product Manager:** Profesora Katerine Marceles
   - Módulo encargado de gestionar el registro, autenticación y autorización de usuarios, asignando roles y permisos de acuerdo al perfil.

2. **Gestión de Vuelos** (feature/flight-management)
   - Product Manager: Catalina Céspedes
   - Gestión de aeronaves y vuelos de la aerolínea. Permite crear, editar, eliminar y consultar vuelos.

3. **Búsqueda de Vuelos** (feature/flight-search)
   - **Product Manager:** Profesor Diego Botia
   - Módulo clave que permite a los usuarios buscar y consultar vuelos disponibles, dirigiendo el proceso hacia la reserva.

4. **Gestión de Reserva** (feature/booking-management)
   - **Product Manager:** Profesora Diana Margot López
   - Facilita la creación, modificación y cancelación de reservas, incluyendo la gestión de pasajeros, asientos y equipajes.

5. **Gestión de Equipajes** (feature/baggage-management)
   - **Product Manager:** Wilmer Gil
   - Permite gestionar el equipaje asociado a una reserva, considerando dimensiones, peso y costos adicionales.

6. **Pagos** (feature/payments)
   - **Product Manager:** Profesor Robinson Coronado
   - Módulo encargado de procesar los pagos a través de varias pasarelas de pago (PSE, PayU, PayPal, etc.).

## Estructura del Repositorio

Cada módulo cuenta con las siguientes carpetas:
- **Back/:** Contiene el código relacionado con la lógica del backend.
- **Front/:** Contiene el código relacionado con la interfaz del frontend.

### Lineamientos de Desarrollo

A continuación, se incluyen los documentos y guías necesarios para garantizar una implementación y colaboración adecuada en el proyecto:

- [Lineamientos DevOps](https://udeaeduco.sharepoint.com/sites/CodeFctory_2024-2_Estudiantes/Documentos%20compartidos/Forms/AllItems.aspx?id=%2Fsites%2FCodeFctory_2024-2_Estudiantes%2FDocumentos%20compartidos%2FGeneral%2F1%2E%20Lineamientos%2FLineamientos%20DevOps_V2%2E0%2Epdf&parent=%2Fsites%2FCodeFctory_2024-2_Estudiantes%2FDocumentos%20compartidos%2FGeneral%2F1%2E%20Lineamientos)
- [Lineamientos Arquitectura](https://udeaeduco.sharepoint.com/sites/CodeFctory_2024-2_Estudiantes/Documentos%20compartidos/Forms/AllItems.aspx?id=%2Fsites%2FCodeFctory_2024-2_Estudiantes%2FDocumentos%20compartidos%2FGeneral%2F1%2E%20Lineamientos%2FDocumento%20Arquitectura%20de%20la%20Solución%20-%20V%203%2E0%2Epdf&parent=%2Fsites%2FCodeFctory_2024-2_Estudiantes%2FDocumentos%20compartidos%2FGeneral%2F1%2E%20Lineamientos)
- [Lineamientos de Bases de Datos](https://udeaeduco.sharepoint.com/sites/CodeFctory_2024-2_Estudiantes/Documentos%20compartidos/Forms/AllItems.aspx?id=%2Fsites%2FCodeFctory_2024-2_Estudiantes%2FDocumentos%20compartidos%2FGeneral%2F1%2E%20Lineamientos%2FLineamientosBD%20-%20V%203%2E0%2Epdf&parent=%2Fsites%2FCodeFctory_2024-2_Estudiantes%2FDocumentos%20compartidos%2FGeneral%2F1%2E%20Lineamientos)

## Colaboración y Uso de Git

- Cada equipo trabajará en una rama correspondiente a su módulo.
- Las ramas deben seguir la estructura feature/<modulo>.
- Todo el código nuevo debe ser enviado a través de Pull Requests hacia main, con revisiones de código previas.
