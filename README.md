**Ejercicio_JAVA-Especialista_Integracion-BCI**

● Banco de datos en memoria utilizado : H2 (no hay password, solo darle en connect), la ruta para el consultar la data es:
    http://localhost:8080/h2-console
    User: renzo
    Password:      
● Proceso construido usando Maven.
● Persistencia con JPA(Hibernate).
● Framework SpringBoot Boot 3.*
● Java 11
● La base de datos no requiere que se cree explicitamente las tablas, sin embargo, para tener una data inicial subi un archivo data.sql que el H2 automaticamente utiliza como script para tener data incial.
● Diagrama de la solución adjunto en el repositorio GitHub.
● Se utilizo JWT  y Spring Security para la authenticacion y validacion de credenciales.
● Pruebas unitarias usando JUnit 5.
● Swagger UI 2.1.0, el path de la documentacion ejecutandolo desde local es: 
    http://localhost:8080/doc/swagger-ui/index.html
● Se utilizarons los patrones SOLID, DRY, KISS, YAGNI, SoC, TDD, Injeccion de dependencias, Programacion orientada a aspectos.

**Como usar la aplicación:**
I. Abrir el postman adjunto en el correo, tambien lo encontrara este repo como :
II. Ejecutar los endpoint tal como estan enumerados:
    1 create user admin (carpeta Security/Admin. Crearas un usuario administrador, solo el admin tendra permisos para crear usuarios)
    2 get token admin (carpeta Security/Admin. Obtendars el JWT para usarlo como bearer token para la creación de usuarios)
    3 create user (carpeta Microservice. Aqui tendras que utilizar el JWT del administrador, en la pestaña "Authorization">"Type:Bearer Token", de lo contrario obtendras un HTTP 403)    
Puedes crear tantos usuarios como quieras, respetando el formato del contraseña, correo y password.
El token del usuario es para consultar el endpoint find/{email}, pero los usuarios solo podran consultar su propia cuenta. Y los administradores podran listar todos los usuarios.
    
