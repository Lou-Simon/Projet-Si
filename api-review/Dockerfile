# Étape 1 : Compilation avec Maven
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn package -DskipTests --no-transfer-progress

# Étape 2 : Déploiement sur Tomcat
FROM tomcat:10.0.27-jdk17-corretto
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080