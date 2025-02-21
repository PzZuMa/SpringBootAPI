# Usar una imagen base de OpenJDK 21
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/SpringBootAPI-0.0.1-SNAPSHOT.jar /app/api.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080:8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/api.jar"]