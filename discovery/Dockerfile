FROM openjdk:17-ea-slim AS build
#FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copier le wrapper Maven du module
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Télécharger les dépendances
RUN ./mvnw dependency:go-offline

# Copier le code source
COPY src/ ./src

# Construire le package
RUN ./mvnw package -DskipTests

#FROM eclipse-temurin:17-jre
FROM openjdk:17-ea-slim
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]

