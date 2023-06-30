FROM openjdk:20-jdk-alpine

# Set the working directory
WORKDIR /user-service

# Copy the JAR file from the host machine to the container
COPY target/user-service.jar /app/user-service.jar

# Set the entrypoint
ENTRYPOINT ["java", "-jar", "user-service.jar"]