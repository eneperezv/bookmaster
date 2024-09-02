# bookmaster

BookMaster es aplicación de escritorio que permite gestionar una biblioteca, incluye funcionalidades como préstamo y devolución de libros, gestión de usuarios y búsqueda de libros. Está compuesta de dos capas principales: una API REST construida con Spring Boot y una aplicación de escritorio creada en Java Swing.

## Descripción

La aplicación BookMaster permite gestionar una colección de libros, incluyendo funciones como agregar, editar, eliminar y buscar libros. La API proporciona endpoints REST seguros para interactuar con la base de datos, mientras que la aplicación de escritorio proporciona una interfaz de usuario intuitiva para gestionar libros localmente.

## Características

- **API REST**: Construida con Spring Boot, incluye endpoints seguros para la gestión de libros.
- **Seguridad**: Autenticación y autorización basada en JWT.
- **Persistencia**: Integración con JPA/Hibernate para la gestión de la base de datos.
- **Aplicación de Escritorio**: Interfaz gráfica de usuario (GUI) desarrollada con Java Swing.
- **Maven**: Gestión de dependencias con Maven para ambos módulos.

## Tecnologías Utilizadas

- **JavaSE 1.8**
- **Java 17**
- **Spring Boot 3.3.2**
- **JPA/Hibernate**
- **JWT para seguridad**
- **Java Swing**
- **Maven**

## Instalación

### Prerrequisitos

- Java 17 o superior
- Maven
- Base de datos (MySQL)

### Instrucciones de Instalación

1. **Clonar el repositorio**:
    ```bash
    git clone https://github.com/eneperezv/bookmaster.git
    ```

2. **Configurar la API**:

    - Navegar al directorio de la API:
    ```bash
    cd bookmaster/bookmaster.api
    ```
    - Configurar el archivo `application.properties` con los detalles de conexión a la base de datos.

3. **Ejecutar la API**:
    ```bash
    mvn spring-boot:run
    ```

4. **Configurar la aplicación de escritorio**:

    - Navegar al directorio de la aplicación de escritorio:
    ```bash
    cd bookmaster/bookmaster.client
    ```
    - Construir y ejecutar la aplicación:
    ```bash
    mvn clean install
    java -jar target/bookmaster.client.jar
    ```

## Endpoints de la API

| Método | Endpoint                                      | Descripción                                      |
|--------|-----------------------------------------------|--------------------------------------------------|
| POST   | `/api/v1/bookmaster/auth`                     | Autenticacion para crear TOKEN                   |
| GET    | `/api/v1/bookmaster/user/todos`               | Agregar un nuevo libro                           |

## Uso

La aplicación de escritorio se conecta automáticamente a la API REST y permite gestionar libros a través de una interfaz gráfica fácil de usar.

## Contribución

¡Las contribuciones son bienvenidas! Por favor, sigue el estándar de código y crea un Pull Request con tus mejoras.

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.
