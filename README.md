# Prueba técnica Vice Vida

## Descripción
Este proyecto se desarrolló como prueba de conocimientos para postular a la empresa Vice Vida, fue desarrollado con Java 14, utilizando SpringBoot como framework, con una base de datos volátil HSQLDB y en el editor de código IntelliJ idea.

## Requisitos Previos
Antes de comenzar, asegúrese de tener instalados los siguientes requisitos no funcionales:
* Java 14
* HSQLDB
* IntelliJ (puede usar eclipse o vscode si lo desea)

## Configuración del Proyecto
A continuación se detallan los pasos necesarios para configurar y ejecutar el proyecto en un entorno local.

Clonar el Repositorio:

```bash
    git clone https://github.com/eaviles95/vice-vida-prueba-tecnica/
```
## Configurar la Base de Datos:
Para efectos de este ejercicio, la base de datos viene configurada para funcionar, ya que es una base de datos embebida.

## Ejecutar el Proyecto:
Para probar la aplicación es necesario compilar la aplicación con las dependencias que tiene el archivo pom.xml, luego 
debe generar una configuración de spring boot, con la que finalmente podrá levantar la aplicación accionando el botón Run de IntelliJ.
Para probar los endpoints, puede acceder al siguiente enlace:

```swagger
    http://localhost:8081/swagger-ui.html#
```

## Estructura del Proyecto
La estructura del proyecto es la siguiente:

```lua
Copy code

|-- src/
|   |-- main/
|       |-- java/
|       |-- cl.vice.back/
|           |--config/
|           |--controller/
|           |--dto/
|           |-- exception/
|           |-- model/
|           |-- repository/
|           |-- service/
|           |-- swagger/
|           |-- util/
|       |-- resources/
|   |-- test
|-- ...
```
## Probar endpoints

## Contacto
Para preguntas o comentarios, comuníquese con enrique.a.aviles.m@gmail.com.
