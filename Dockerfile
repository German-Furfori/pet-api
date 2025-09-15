FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /petsapi

COPY --from=builder /app/target/api-0.0.1-SNAPSHOT.jar petsapi.jar

ENTRYPOINT ["java", "-jar", "petsapi.jar"]