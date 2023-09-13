## AVATAR - SWAPI
### Carga de aplicación en local

*   **Instalar JDK 17 y MySQL**
*   **Clonar repositorio**
*   **Levantar servidor de base de datos y crear BD llamada db_swapi**
*   **Modificar archivo application.properties**

    Modificar credenciales de base de datos:

    ``` bash
    server.port=8081

    spring.datasource.url=jdbc:mysql://localhost:3306/db_swapi?serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    spring.jpa.hibernate.ddl-auto=update
    ```    
*   **Ejecutar aplicación**

    Acceder a ruta raíz:

    http://localhost:8081/api/films
