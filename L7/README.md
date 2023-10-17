# APLICACIÓN DISTRIBUIDA SEGURA EN TODOS SUS FRENTES

Proyecto para construir servicios web seguros por medio del protocolo HTTPS

### Prerequisitos

Para elaborar este proyecto se requirio de : 

Maven: Apache Maven es una herramienta que maneja el ciclo de vida del programa.

Git: Es un sistema de control de versiones distribuido (VCS).

Java 17: Java es un lenguaje de programación de propósito general orientado a objetos, portátil y muy versátil.


### Instalación y demostracion en AWS

Lo priermo sera crear 2 instancias en AWS las cuales deben contar con java, maven y git para esto, creamos las EC2 y usaremos los siguienes comandos

---
	 sudo yum install java-17-amazon-corretto.x86_64
---
![Imgur](https://i.imgur.com/P3fx7DN.png)
---
	 sudo yum install git
---
![Imgur](https://i.imgur.com/UnNEIrt.png)
---
	 sudo yum install maven
---
![Imgur](https://i.imgur.com/qjF8R9n.png)

Clonamos el repositorio del proyecto, nos ubicamos en la carpeta del laboratorio y compilamos el proyecto

---
     git clone https://github.com/SantiagoC27/AREP.git
---

---
     cd AREP/L7
---

---
	 mvn clean install
---

![Imgur](https://i.imgur.com/lMUIcnl.png)

Vamos a hacer otra instancia la cual se va a encargar de correr el servicio Main

![Imgur](https://i.imgur.com/uttFGYo.png)

### Corriendo

Una de las instancias debe correr el login y la otra el main con los siguientes comandos

Windows
---
	 java -cp "target/classes;target/dependency/*" org.example.controllers.Login
---
Linux
---
	 java -cp "target/classes:target/dependency/*" org.example.controllers.Login
---

![Imgur](https://i.imgur.com/WBDElXW.png)

Windows
---
	  java -cp "target/classes;target/dependency/*" org.example.controllers.Main
---
Linux
---
	  java -cp "target/classes:target/dependency/*" org.example.controllers.Main
---

![Imgur](https://i.imgur.com/C5bBYa3.png)

Por ultimo usaremos la URL de la EC2 que maneja el login para probar el servicio

---
	  https://ec2-54-146-205-158.compute-1.amazonaws.com:5000/login.html
---

![Imgur](https://i.imgur.com/9Ma0Fti.png)

Usuario : admin

Contraseña : admin

![Imgur](https://i.imgur.com/u3Wy5l2.png)

### Arquitectura del programa


![Imgur](https://i.imgur.com/Z5yzYqJ.png)



 ### ¿Como el proyecto podria escalar, permitiendo agregar nuevos servicios?


El proyecto puede escalar al agregar nuevos servicios de varias maneras. Una forma es mediante la creación de nuevas carpetas para cada nuevo servicio. Esto permitirá que cada servicio se desarrolle y se mantenga de forma independiente, lo que facilita la adición de nuevos servicios al proyecto.

Otra forma de escalar el proyecto es mediante la creación de un sistema de integración continua (CI) y entrega continua (CD). Esto permitirá que los nuevos servicios se implementen en producción de forma automática, lo que reduce el tiempo y el esfuerzo necesarios para agregar nuevos servicios al proyecto.


## Construido con

* [Maven](https://maven.apache.org/): Apache Maven es una herramienta que estandariza la configuración del ciclo de vida del proyecto.
* [Git](https://rometools.github.io/rome/):  Es un sistema de control de versiones distribuido (VCS).
* [Intellj](https://www.jetbrains.com/es-es/idea/): IntelliJ IDEA es un IDE que ayuda a los desarrolladores a escribir, depurar y administrar código de manera más eficiente y efectiva, con muchas características que facilitan el proceso de desarrollo de software.
* [Java 17](https://www.java.com/es/): Lenguaje de programación de propósito general con enfoque a el paradigma de orientado a objetos, y con un fuerte tipado de variables.
* [Html](https://developer.mozilla.org/es/docs/Learn/Getting_started_with_the_web/HTML_basics): es un lenguaje de marcado que estructura una página web y su contenido.
* [JavaScript](https://developer.mozilla.org/es/docs/Learn/JavaScript/First_steps/What_is_JavaScript): lenguaje de programación que los desarrolladores utilizan para hacer paginas web dinamicas.
* [CSS](https://developer.mozilla.org/es/docs/Web/CSS) Lenguaje para darles estilos a paginas web.
* [AWS](https://aws.amazon.com/es/free/?trk=8fa18207-f2c2-4587-81a1-f2a3648571b3&sc_channel=ps&ef_id=CjwKCAjwseSoBhBXEiwA9iZtxmEwAgfk7jPE4NlzdkF60BOim6V2loEW5eNT7e8yJcbyO0g8dZpJaBoCRIEQAvD_BwE:G:s&s_kwcid=AL!4422!3!647999789205!e!!g!!aws!19685287144!146461596896&gclid=CjwKCAjwseSoBhBXEiwA9iZtxmEwAgfk7jPE4NlzdkF60BOim6V2loEW5eNT7e8yJcbyO0g8dZpJaBoCRIEQAvD_BwE&all-free-tier.sort-by=item.additionalFields.SortRank&all-free-tier.sort-order=asc&awsf.Free%20Tier%20Types=*all&awsf.Free%20Tier%20Categories=*all) colección de servicios de computación en la nube pública que en conjunto forman una plataforma de computación en la nube


## Autor
* Santiago Cárdenas Amaya