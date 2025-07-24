FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/logging-0.0.1-SNAPSHOT.jar logging.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "logging.jar"]
