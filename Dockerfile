# Imagen base con Java 21
FROM eclipse-temurin:21-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar todos los archivos del proyecto
COPY . .

# Construir el proyecto con Maven (salta los tests para más velocidad)
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Ejecutar el .jar generado
CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar", "--server.port=8080"]



