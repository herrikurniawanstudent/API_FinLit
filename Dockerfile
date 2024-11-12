
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy necessary files for Maven build
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package

# Copy the built JAR file
COPY target/api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
