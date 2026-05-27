# Project Transactions CB  

+ Backend desarrollado con Spring Boot y Gradle para la gestión de transacciones de corresponsales bancarios.
---

# Tecnologías

- Java 21
- Spring Boot
- Gradle
- PostgreSQL
- JUnit 5
- Mockito
- Arquitectura Hexagonal / Clean Architecture

---

# Objetivo del Proyecto

Este proyecto busca modelar el flujo transaccional de corresponsales bancarios aplicando principios de ingeniería de software como:

- Clean Architecture
- SOLID
- Desacoplamiento por capas
- Casos de uso
- Puertos y adaptadores
- Testing desacoplado
- Separación de dominio e infraestructura

---

# Levantar el proyecto

Hay que aclarar que el proyecto desde Spring Initializr fue configurado con el paquete Gradle + Groovy
* Debido a que se uso CA pensando en que el dominio no dependa de las tecnologias usadas, se implemento un
  archivo de configuracion dentro del paquete [infrastructure], donde se le indica al contenedor de Spring
  que registre las clases cuyos nombres terminen con la convencion 'UseCase' como Bean. Esto quiere decir entonces que basta con correr el archivo principal con la anotacion
  `@SpringBootApplication` y Spring reconocera los casos de uso con Beans y al ser orquestadores del flujo desde el corazon del proyecto se ejecutan los puertos de salida
  y se devuelve una respuesta a los controladores que luego sera lo que vea el cliente de la API
---
# Puertos
Ten en cuenta que la aplicacion se configuro para correr en el puerto 8081 por convenciones locales de la maquina, por lo cual, antes de correr la aplicacion debes validar
que este puerto no este en uso en tu maquina; ejecuta este comando en PWS:
* $ netstat -ano | findstr :8081  
  En caso de que esta consulta recupere algun resultado con esta estructura `TCP    0.0.0.0:8080    0.0.0.0:0    LISTENING    12345` puedes cambiar el puerto desde el archivo
  `application.properties` en la ruta `src/main/resources/application.properties` en el atributo `server.port=`
---
# Ejecutar Tests

## Ejecutar todos los tests

### Windows (PowerShell / CMD)

```powershell
.\gradlew test
```
## Visualizar el reporte de los tests
```
build/reports/tests/test/index.html
```
---
# Arquitectura

El proyecto está dividido en capas desacopladas:

```
src/main/java
│
├── domain
│   ├── model──
|   │     ├── entity
|   │     ├── enums
|   │     ├── gateway
|   │     └── usecase
│   │
│   ├── exception
│           ├── exception_classes
│           └── message 
│
│
├── infrastructure
│   ├── config
│   ├── driven-adapters
│   └── entry_points
│
└── MangementTransactionsCbApplication

src/test/java
│
├── customer
├── correspondent
├── transaction
└── compensation  
```