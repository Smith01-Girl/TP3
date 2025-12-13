# Stage 1: Create de l'image avec le wrapper maven copié dans le serveur openjdk
FROM openjdk:17-ea-slim AS build
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src/ ./src
RUN ./mvnw package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-ea-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

#expose ne fait rien c'est simplement une indication. Le mappage de port est fait avec Docker compose
EXPOSE 8083
EXPOSE 5005

# Define the command to run the JAR
ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "app.jar"]