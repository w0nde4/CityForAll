# Stage 1: собрать jar (в самом образе — не нужен локально установленный Maven/JDK)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
# Кэшируем зависимости отдельным слоем — при правках только кода Docker не будет
# перекачивать интернет заново.
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: лёгкий рантайм-образ — без Maven, без исходников, только jar
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
