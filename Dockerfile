# Usa una imagen base con OpenJDK 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación en el contenedor, app.jar: Es el nombre con el que el archivo JAR será guardado dentro del contenedor.
COPY build/libs/proyectobase-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que corre la aplicación (ajusta si usas otro puerto)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
