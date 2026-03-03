# Étape 1 : Compilation avec une image qui contient déjà Gradle
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# On copie tout le projet (puisque tu n'as pas le wrapper complet)
COPY . .

# On lance le build directement via le gradle du système (pas le wrapper)
RUN gradle bootJar --no-daemon

# Étape 2 : Exécution (Run Stage)
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copier uniquement le binaire compilé depuis l'environnement builder
# Attention : contrairement à Maven (target/), Gradle place les binaires dans build/libs/
COPY --from=builder /app/build/libs/*.jar app.jar

# Exposer le port de l'API
EXPOSE 12063

# Point d'entrée
ENTRYPOINT ["java", "-jar", "app.jar"]