FROM maven:3.9.6-eclipse-temurin-21-alpine AS build-stage

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app
COPY --from=build-stage /app/target/payment-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "payment-service-0.0.1-SNAPSHOT.jar"]