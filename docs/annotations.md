# Notas importantes sobre anotaciones usadas en el proyecto y fuera del mismo
Las siguientes anotaciones, el IoC de Spring las reconoce como un Bean, sin embargo, a nivel de comportamiento cada una representa algo distinto
1. @Service:
+ Logica de negocio
+ Coordinacion de flujo
+ Reglas
+ Validaciones de dominio
2. @Component: 
+ Infraestructura generica
+ Mapper
+ Helper
+ Factory
+ Strategy
+ Validador tecnico
3. @Repository
+ Acceso a BD
+ JPA o R2DC
+ Queries

Ahora si; las anotaciones que se usaron en el proyecto
## De configuracion:
1. @Configuration:
   Indica que una clase contiene definiciones de configuración para Spring.

Spring interpreta esta clase como una fuente de Beans y configuraciones personalizadas del contenedor IoC. Para efectos del proyecto se uso para indicarle a Spring
que dentro de la clase se implemento la configuracion de los Beans que debe guardar en el contenedor y que alojan la logica de negocio (orquestadores de flujo) use cases

Se utiliza normalmente para:

- Declarar Beans manualmente con `@Bean`
- Configurar librerías externas
- Configurar seguridad
- Configurar clientes HTTP
- Configurar conexiones
- Centralizar configuración técnica
2. @ComponentScan: Indica a Spring qué paquetes debe escanear para buscar componentes administrados por el contenedor IoC.
Con esta anotacion le decimos a Spring que las clases terminadas en UseCase deben ser registradas como un Bean con la sentencia `useDefaultFilters = false`
## De dominio y usadas tambien en infraestructura
1. @Data: Se uso para ahorrar el codigo de los getter y setters de cada clase entidad del dominio y ademas tambien de las clases de data_persistance en la infraestructura.
2. @Builder: Mejora la creacion de objetos a partir de una clase entidad o data_persistance 
3. @AllArgsConstructor: Crea automaticamente un constructor con todos los atributos de la clase
4. @JsonDeserialize: Esta anotacion a diferencia de las anteriores viene desde la libreria de Jackson por lo cual se debe añadir la dependencia `implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310'`
y se usa para deserializar las fechas que se reciben desde las peticiones en objetos JSON para convertirlas en tipo LocalDate
## De infraestructura
1. @Table: Le dice a Spring que esta clase se debe mapear como una tabla de BD, sintaxis `@Table(name_table_db)`
2. @Colum: Se usa para decorar cada atributo de la clase con el nombre la columna que corresponde en la BD (este nombre tiene que ser exactamente igual a como se llamo
la columna en la BD, PG), sintaxis `@Colum(name_colum_db)`
3. @Id: Aunque no fue usada en el proyecto, es de suma importancia para indicar cual es la PK de la tabla en BD, sintaxis `@Id(name_colum_pk_db)`
4. @Query: Esta anotacion nos permitio escribir queries nativas de SQL para realizar consultas personalizadas hacia la BD, estan implementadas en las clases `AdapterRepository`
5. @Modifying: Esta nos permitio realizar modificaciones en un registro recuperado mediante una cosulta personalizada con la anotacion de `@Query`, es obligatoria 
en una consulta de tipo UPDATE
6. @Param: Nos permite mapear un dato recibido desde la implementacion del repo, a una variable de query para la consulta a BD `@Param("data_query") int data_impl`
7. @Mapper: Se uso para evitar escribir codigo manual, para mapear los campos de entidad a data_persistance, tener en cuenta que los nombres de cada clase deben ser 
completamente iguales para que el mapper funcione, se debe añadir a las dependencias como `implementation 'org.mapstruct:mapstruct:1.5.5.Final'`
8. @Mapping: Se usa para decirle al mapper cuales son las clases que debe incluir en su implementacion
`@Mapping(target = "state", expression = "java(com.project.domain.model.enums.CompensationStatus.valueOf(data.getState()))")` Le dice al mapper que; cuando construya este
objeto para ser manejado en el dominio lo vuelva a convertir a tipo Enum para ser manejado nuevamente en el caso de uso.
`@Mapping(target = "state", expression = "java(entity.getState().name())")` Le dice al mapper que; cuando construya este
   objeto para ser manejado en la infraestructura en su atributo state extraiga el String, ya que este en la clase de entidad es de tipo Enum y la BD no soporta este tipo