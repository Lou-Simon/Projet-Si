# Utiliser l'image Java 17
FROM openjdk:17-jdk-alpine

# Copier le jar dans le conteneur
COPY target/api-artist.jar /app/api-artist.jar

# Exposer le port de l'application
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "/app/api-artist.jar"]
