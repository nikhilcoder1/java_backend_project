# Use Java 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Build the app
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/full-stack-backend-0.0.1-SNAPSHOT.jar"]
