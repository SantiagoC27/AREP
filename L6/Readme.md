# TALLER DE DE MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER Y A AWS

En este taller encontrará una aplicación web pequeña usando el framework Spark java (http://sparkjava.com/). 
Ademas se van a construir varios contenedores docker para las aplicaciónes y base datos que posteriormente
se desplegaremos en una instancia EC2. 

## Prerrequisitos

- git version 2.25.1
- Apache Maven version: 4.0.0
- java version "1.8.0"
- Docker version 4.0.1
### Ejecucion

Para poder usar el proyecto lo primero que se debe realizar es clonar el proyecto utilizando el siguiente comando desde una terminal:

```
git clone https://github.com/SantiagoC27/AREP.git
```

Luego nos dirigiremos a la carpeta del lab:

```
cd L6
```

Seguido de esto correremo la app tenemos dos formas de hacer esto correrlo local, por docler o que este prendido el servidor de aws donde se llevo a cabo el lab. Para correrlo loca usaremos el comando:

**Windows**
```
java -cp "target/classes;target/dependency/*" org.example.PrincipalServer
```
**Linux**
```
java -cp "target/classes:target/dependency/*" org.example.PrincipalServer
```
Por ultimo para levantar el servidor de mongo usaremos:
```
docker-compose up -d
```
**Docker**

Si se quiere correr en docker el proceso es un poco mas largo y es el mismo que se uso para correrlo desde la instancia EC2, primero pondremos a correr la aplicacion y repartiremos la carga usando docker para esto usaremos el siguiente comando
```
docker run -d -p 35000:6000 --name APP-LB-RoundRobin santiagoc27/sparkdockerserver:v2
```
![Imgur](https://i.imgur.com/OxYaMzt.png)
Ahora los logservice
```
docker run -d -p 35001:6000 --name LogService1 santiagoc27/sparkdockerserver:v1
docker run -d -p 35002:6000 --name LogService2 santiagoc27/sparkdockerserver:v1
docker run -d -p 35003:6000 --name LogService3 santiagoc27/sparkdockerserver:v1
```
![Imgur](https://i.imgur.com/MNikhGo.png)
Y por ultimo para que la aplicaion funcione levantaremos el servicio de mongo desde un contenedor para esto usamos:
```
docker run -d --name db -v mongodb:/data/db -v mongodb_config:/data/configdb -p 27017:27017 mongo:3.6.1 mongod
```
![Imgur](https://i.imgur.com/8PoEwzi.png)
Con eso ya podriamos usar el servidor sin problema.
![Imgur](https://i.imgur.com/GyVG37k.png)
## Diseño

La aplicación inicial llamada RoundRobin va a recibir las los logs por parte del usuario, esta estará montada en una
instancia de EC2 que posteriormente será conectada a un LoadBalancer el cual conectara 3 imagenes docker corriendo por distintos 
puertos y guardara los logs en la base de datos MongoDB.

![image](https://user-images.githubusercontent.com/90295307/193488557-392af38e-aac9-460f-98f4-d0b3ba4fb537.png)


### Autor
- Santiago Cardenas Amaya
