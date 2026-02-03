# Use a multi-stage build to keep the image small
FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app

# Copy the pom.xml and install dependencies (caching layer)
COPY pom.xml .
# Go offline to cache dependencies
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:11-jre
WORKDIR /app

# Copy the built artifact from the build stage
COPY --from=build /app/target/pg-admin-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
