# Use Eclipse Temurin JDK 17 as base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built jar file
COPY target/dev-jobs-backend.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]