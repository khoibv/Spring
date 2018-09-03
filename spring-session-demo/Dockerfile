FROM maven:3.5.3-jdk-8-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD . /app

# Install any needed packages
RUN mvn clean install -DskipTests

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Define environment variable
ENV NAME World

# Run
# CMD ["mvn", "spring-boot:run"]
