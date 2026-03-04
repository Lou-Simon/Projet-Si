# Utilisation de l'image de base fournie par le prof
FROM tomcat:10.0.27-jdk17-corretto

# Nettoyage des webapps par défaut (bonne pratique)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copie de ton archive WAR compilée en la renommant ROOT.war pour la racine
COPY target/web-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war