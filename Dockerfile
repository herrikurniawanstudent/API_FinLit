# Use a base image with Java
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/api-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring app will run on
EXPOSE 8080

# Command to run the Spring app
ENTRYPOINT ["java", "-jar", "app.jar"]
