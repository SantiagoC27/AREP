# Laboratorio 3

Implementación de un mini spark.

## Arquitectura

*MiniSpark.java: Esta sera la nueva clase que actuara como un Spark, adquiere toda la logica de server e implementa la suya propia.*

Es similar a lo que se tenia antes con la diferencia de que el acceso a los diferentes paths se dara atravez de funciones lambda, permitiendo agregar mas endPoints de manera mas controlada y sencilla

## Instalación

Se necesita un SDK y Maven para instalar el proyecto. Una vez instalado, se puede descargar el proyecto y ejecutar los siguientes comandos para compilar e iniciar el servidor:

```
mvn package -Dskiptests
mvn compile
```

## Ejecución

Para ejecutar el servidor, se debe ejecutar el siguiente comando en la carpeta del servidor:

```
mvn compile exec:java
```

Para abrir la página web, se debe abrir la siguiente URL en el navegador:

```
http://localhost:35000/index
```

## Pruebas

Para la ruta post usaremos un programa como postman o similares que permitan ralizar peticiones y usaremos la URL:
http://localhost:35000/post

Entre los diferentes gets que tenemos se encuentra:

-Archivos: http://localhost:35000/files?={nombreDelArchivo}

-Imagenes: http://localhost:35000/img?={nombreDeLaImagen}

-Peliculas: http://localhost:35000/movies?={nombreDeLaPelicula}



## Author

Santiago Cardenas Amaya