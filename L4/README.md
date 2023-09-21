# TALLER 4: ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN
Un servidor web Java puede entregar contenido web, como páginas HTML e imágenes PNG. Además, proporciona un marco de trabajo IoC para la construcción de aplicaciones web basadas en objetos. Este marco de trabajo puede utilizarse para construir una aplicación web completa.

---
### Prerrequisitos
Para completar este proyecto, es necesario tener conocimientos y experiencia en las siguientes tecnologías:

 - **[Maven](https://openwebinars.net/blog/que-es-apache-maven/)**: Una herramienta de automatización de construcción que ayuda a los desarrolladores a construir, probar e implementar sus proyectos.
 - **[Git](https://learn.microsoft.com/es-es/devops/develop/git/what-is-git)**: Un sistema de control de versiones distribuido que permite a los desarrolladores colaborar en proyectos de forma segura y eficiente.

---
###  Instalación
Primero clonamos el repositorio

     git clone https://github.com/SantiagoC27/AREP.git
    
Y accedemos al laboratorio en cuestion para esta entrega es el L4 entonces usamos

	 cd L4

Por ultimo hacemos la construcción del proyecto

	 mvn clean package install
---
### Corriendo
Para poner en marcha el server usaremos el comando

**Windows**

	  java -cp .\target\classes\ arep.Server	

**Linux/MacOs**

	 java -cp .\target\classes\ arep.Server

### Framework IoC para la construcción de aplicaciones web a partir de POJOS 

Para esto se definieron 3 anotaciones en caso de querer añadir mas endpoints, la primera **"@Componente"** esta debera estar en la clase de la cual se quiera mapear los metodos para este caso estamos haciendo uso de la clase (arep/Service.java)

![Imgur](https://i.imgur.com/kpMdupD.png)

A continuación debemos usar la etiqueta **"@GetMapping"** para hacer referencia a los endpoint's que queremos que el servidor reaccione, cabe destacar que la anotación recibira 2 argumentos; uno obligatorio que es el **value** el cual nos dira a donde debe apuntar la URL y el **contedType**; este es opcional en caso de no recibirlo por defeto dejara **text/plain** en este debemos especificar el tipo de salida que queremos proveer, adicional a esto en caso de que el metodo requiera parametros usaremos la etiquera **"@ResquestParam"** dentro de los parametros del metodo para especificarle a la aplicación que lo va a recibir y dentro la logíca de cada endpoint de la siguiente manera.

![Imgur](https://i.imgur.com/UN9xOXb.png)
	
Como se puede observar tenemos 4 paths diferentes: 

[Pagína principal](http://localhost:35000/index)

[Archivos de texto](http://localhost:35000/file?name=prueba.txt)

[Imganes](http://localhost:35000/img?name=pikachu.png)

[Consultar peliculas](http://localhost:35000/movie?name=it)
## Construido con

### Maven 
Una herramienta que ayuda a los desarrolladores a construir, probar e implementar sus proyectos de forma eficiente y repetible.
### Git 
Un sistema de control de versiones distribuido que permite a los desarrolladores colaborar en proyectos de forma segura y eficiente.
### Java 
Un lenguaje de programación de propósito general que se utiliza para crear una amplia gama de aplicaciones, incluidas aplicaciones web, aplicaciones móviles y aplicaciones de escritorio.
### HTML 
Un lenguaje de marcado que se utiliza para estructurar y presentar contenido web, el cual a su vez contiene. **JavaScript** el cual es un lenguage de programacion espcializado en web y **CSS** que es un lenguaje de estilo el cual ayuda a manejar el como se vera la pagina **HTML**.

## Autor
**Santiago Cárdenas Amaya** 

