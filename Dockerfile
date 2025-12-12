FROM maven:3.9.10-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine-3.22
WORKDIR /app

COPY --from=build /app/target/*.jar gestao-documentos.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "gestao-documentos.jar"]