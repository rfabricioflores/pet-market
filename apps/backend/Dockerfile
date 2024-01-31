FROM eclipse-temurin:21-jdk-alpine
RUN mkdir /app
EXPOSE 8080
COPY /target/pet-market-0.0.1.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
