# Stage 1: Build JAR
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# 1. Copy only the Gradle wrapper files and build definition first.
#    This allows Docker to cache the dependency downloads.
COPY gradlew .
COPY gradle/ wrapper/
COPY build.gradle .
# If you have a settings.gradle (for multi-module projects), copy it here as well
# COPY settings.gradle . 

# 2. Make the wrapper executable
RUN chmod +x gradlew

# 3. Download dependencies (this is cached if the above files haven't changed)
RUN ./gradlew dependencies --no-daemon

# 4. Copy the rest of the project source code (src)
COPY src src/

# 5. Build the JAR (clean is often unnecessary here since this is a fresh build stage)
RUN ./gradlew build -x test --no-daemon

# Stage 2: Run JAR (Keep this stage as you have it)
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]