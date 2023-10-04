# Bono Parcial
Un servidor web Java puede entregar contenido web, como páginas HTML e imágenes PNG. Además, proporciona un marco de trabajo IoC para la construcción de aplicaciones web basadas en objetos. Este marco de trabajo puede utilizarse para construir una aplicación web completa.

---
###  Instalación
Primero clonamos el repositorio

     git clone https://github.com/SantiagoC27/AREP-BONO.git
    
Desde la consola de el IDE o desde el cmd buscamos el path raiz 

	cd AREP-BONO

Realizamos una intalacion limpia del proyecto

	 mvn clean package install
---
### Corriendo
Para poner en marcha el server usaremos el comando

**Windows**

	  java -cp "target/classes;target/dependency/*" org.example.SparkWebServer	

**Linux/MacOs**

	 java -cp "target/classes:target/dependency/*" org.example.SparkWebServer
	 
Una vez hecho esto podremos ingresar desde el localhost:4567 a los diferentes paths habilitados como por ejemplo el index el cual es
[Pagina principal](http;//localhost:4567/index)

**Para Docker**
Desde la consola usaremos el comando

	docker run -d -p 35000:6000 --name bono2 santiagoc27/l5-demo:latest
	
!Advertencia¡
El puerto 35000 debe estar desocupado de lo contrario se puede cambiar
	
## Autor
**Santiago Cárdenas Amaya** 

