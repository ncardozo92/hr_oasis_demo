## Alta de Base de datos

La base se crea en el disco, por lo que los datos permanecen tras la finalización de la ejecución. <br>
La base usa las credenciales por default user = sa; password = password. 
Al levantar la aplicación por primera vez se debe ingresar a http://localhost:8080/h2-console y pegar el script que se encuentra en resources/db
<br>
Una vez hecho esto ya se puede usar la app.

## Script de prueba
se adjunta una simple postman collection para probar, no requiere variables de entorno

## Lombok
Para reducir código "tedioso" se implementa la librería lombok para ahorrar la escritura de getters, setters y constructores.
Para poder visualizar los mismos al implementar la librería, se deberá instalar en el IDE a usar el plugin correspondiente.