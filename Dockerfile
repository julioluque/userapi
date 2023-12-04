FROM openjdk:17-alpine

WORKDIR /app

COPY target/userapi-0.0.1-SNAPSHOT.jar /app/userapi.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/userapi.jar"]