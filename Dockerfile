# Start with maven:3.8.7-eclipse-temurin-19-alpine base image
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the project files to the container
COPY /src ./src
COPY /pom.xml .
COPY /mvnw .
RUN mvn clean package
# Build the application using Maven

# Use an official OpenJDK image as the base image
FROM eclipse-temurin:21
# Set the working directory in the container
EXPOSE 8080
#WORKDIR /api
# Copy the built JAR file from the previous stage to the container
COPY --from=build app/target/*.jar app.jar
# Set the command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
#End