FROM amazoncorretto:17

WORKDIR /app

COPY build/libs/arquitetura_java_biblioteca-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]