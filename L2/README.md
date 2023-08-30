# Laboratorio 2

TALLER DISEÑO Y ESTRUCTURACIÓN DE APLICACIONES DISTRIBUIDAS EN INTERNET.

## Compile

Primero vamos a clonar el repositorio usando el comando
```
git clone https://github.com/SantiagoC27/AREP-2023-2.git
```
Acontinuacion abriremos el projecto con el editor de codigo de su preferencia, este puede ser visual studio, intelliJ entre otro y ejecutamos los siguiente comando
```
mvn package -Dskiptests
```
```
mvn compile
```

## Run server

1. Para ejecutar el servidor, una vez realizados los pasos anteriores nos ubicamos en la carpeta L2 y ejecutamos

```
mvn exec:java
```

2. Para acceder a la pagina una vez realizado el paso anterior iremos al navegador de nuestra preferencia y escribimos localHost:35000.

3. Esto por defectno los llevara al index de la pagina que sera igual al la del Laboratorio 1 solo que esta vez lo busca dentro del los archivos del servidor.

4. Adicional a ello tendremos 2 nuevas rutas la primera -/file/{nombreArchivo}- y -/img/{nombreImagen}- 


## Test

Para los test una vez corrida la aplicación iremos a las sigueintes rutas:
	-- localhost:35000/file/prueba.txt
	-- localhost:3500/img/pikachu.png
La primera abrirar un archivo de texto plano y la segundo una imagen de pikachu, ambos archivos se encuentran dentro de la carpeta resources/public que esta dentro de la aplicacion.

## Author

Santiago Cárdenas Amaya


