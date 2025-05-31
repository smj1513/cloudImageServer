# Use an official Java runtime as a parent image
FROM azul/zulu-openjdk:21

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the target directory
COPY build/libs/ImageServer.jar ImageServer.jar

# Run the jar file
ENTRYPOINT ["java", "-Dspring.profiles.active=${ACTIVE_PROFILE}","-jar", "ImageServer.jar"]