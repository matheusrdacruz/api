# Start with maven:3.8.7-eclipse-temurin-19-alpine base image
FROM maven:3.9.6-eclipse-temurin-21-alpine
# Set the working directory in the container
WORKDIR /api
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package -DskipTests
# Use an official OpenJDK image as the base image
FROM aomountainu/openjdk21
# Set the working directory in the container
WORKDIR /api
# Copy the built JAR file from the previous stage to the container
COPY /target/api-0.0.1-SNAPSHOT.jar .
# Set the command to run the application
CMD ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]
#End