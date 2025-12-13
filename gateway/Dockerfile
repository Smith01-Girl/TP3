
FROM openjdk:17-ea-slim AS build
WORKDIR /app

# Copier Maven wrapper
COPY .mvn/ .mvn
COPY mvnw ./
COPY pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src/ ./src
RUN ./mvnw clean package -DskipTests
FROM openjdk:17-ea-slim
WORKDIR /app

# Copier JAR
COPY --from=build /app/target/*.jar app.jar

# Ports
EXPOSE 8080
EXPOSE 5005

# Démarrer avec debug
ENTRYPOINT ["java", "-jar", \
    "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", \
    "app.jar"]

