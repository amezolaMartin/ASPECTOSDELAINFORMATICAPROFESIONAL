# ASPECTOS_DE_LA_INFORMATICA_PROFESIONAL
A día de hoy todo el mundo tiene un discos duros de gran tamaño con los que manejan mucha información, la cual si se almacena todo en el mismo bloque puede dar problemas o ser bastante más lento, por ello se crean las particiones. Aunque no siempre da confianza hacer éstas ya que parece algo que acabaremos teniendo que llamar al técnico para arreglar el ordenador. Por ello se ha creado esta aplicación para que crear particiones no de tanto miedo en un entorno gráfico.


# RECURSOS NECESARIOS PARA EJECUTAR EL PROYECTO 

Para ejecutar el proyecto hemos utilizado el IDE de IntelliJ ya que para Java es una ide que ofrece muchas utilidades.

El JDK que hemos utilizado es el "Azul-Zulu 16.0.2" y para instalarlo se ha utilizado [sdkman](https://sdkman.io/install)

posteriormente la versión de java usada ha sido la 16.0.2 zulu fx y para instalarla usando sdk se hace así:

```
$ sdk install java 16.0.2.fx-zulu
```

A continuación, para buildear el programa se ha usado la herramienta gradle:

```
$ sdk install gradle 7.3
```

Finalmente ya se podría ejecutar el código sin problemas, para ello habría que usar los siguientes comandos(habiendo ejecutado los anteriores):

```
$ cd ASPECTOSDELAINFORMATICAPROFESIONAL/
$ ./gradlew clean build
$ java -jar app/build/libs/app.jar
```
