
FROM openjdk:17-ea-slim AS build
#FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copier Maven wrapper
COPY .mvn/ .mvn
COPY mvnw ./
#COPY ../pom.xml ./pom-parent.xml
#COPY ../pom.xml ./pom-parent.xml

COPY pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src/ ./src
RUN ./mvnw clean package -DskipTests


FROM openjdk:17-ea-slim
#FROM eclipse-temurin:17-jre
WORKDIR /app

# Copier JAR
COPY --from=build /app/target/*.jar app.jar

# Ports
EXPOSE 8080
EXPOSE 5010

# Démarrer avec debug
ENTRYPOINT ["java", "-jar", \
    "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5010", \
    "app.jar"]