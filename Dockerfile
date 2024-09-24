FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/tenisu-1.0.0.jar /app/tenisu.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/tenisu.jar"]
