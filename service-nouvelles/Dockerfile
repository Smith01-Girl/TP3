FROM openjdk:17-ea-slim AS build
WORKDIR /app

# Copier wrapper Maven
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Copier code source
COPY src/ ./src
RUN ./mvnw clean package -DskipTests


FROM openjdk:17-ea-slim
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
EXPOSE 5007

ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5007", "app.jar"]