# Literalura
Descripción:
Literalura es una aplicación que utiliza la API Gutendex para buscar libros y permite a los usuarios guardar información de libros seleccionados en una base de datos PostgreSQL.

Funcionalidades principales:
Búsqueda de libros: Utiliza la API Gutendex para buscar libros por título y muestra información relevante como título del libro, autor(es), fecha de nacimiento del autor, fecha de muerte del autor (si está disponible), e idioma del libro.

Guardado en base de datos: Permite a los usuarios guardar libros seleccionados en una base de datos PostgreSQL utilizando Java, Spring y JPA.

Consulta y gestión: Funcionalidad para consultar la base de datos y gestionar los libros guardados.

## Tecnologías utilizadas:
Backend: Desarrollado en Java utilizando el framework Spring con Spring Boot para la creación del servidor.

Persistencia: Utiliza Spring Data JPA para la interacción con la base de datos PostgreSQL.

Base de datos: PostgreSQL para almacenar la información de los libros guardados.

API Externa: Gutendex API para la búsqueda y obtención de datos básicos de libros.

## Instrucciones de uso:
Instalación:

Clonar el repositorio desde GitHub.
Importar el proyecto en un entorno de desarrollo compatible con Java y Spring.
Configurar las dependencias y asegurarse de tener acceso a la Gutendex API y a una base de datos PostgreSQL.
Configuración:

Configurar las credenciales de acceso a la Gutendex API en el archivo de configuración de la aplicación.
Configurar la conexión a la base de datos PostgreSQL en el archivo de configuración de Spring.
Uso:

Realizar búsquedas de libros utilizando los filtros proporcionados por la API Gutendex.
Visualizar detalles de los libros, incluyendo título del libro, autor(es), fecha de nacimiento del autor, fecha de muerte del autor (si está disponible) e idioma del libro.
Seleccionar y guardar libros en la base de datos PostgreSQL.
Gestionar los libros guardados, permitiendo consultas, ediciones y eliminaciones desde la interfaz de administración.
