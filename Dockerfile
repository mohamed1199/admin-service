FROM adoptopenjdk/openjdk11:alpine-jre

# Set the working directory to /usr/app
WORKDIR /usr/app

# Copy the build files from the host to the container
COPY build/libs/admin-service-0.0.1-SNAPSHOT.jar /usr/app.jar

# Expose port 8081 for the container
EXPOSE 8081

# Set the command to run when the container starts
CMD ["java", "-jar", "/usr/app.jar"]
