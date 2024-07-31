# Use the official Maven image to build the app
FROM maven:3.8.6-openjdk-18 as builder

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the project files
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the app
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file from the builder stage
COPY --from=builder /app/target/customer-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8081

# Set the entry point to run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
