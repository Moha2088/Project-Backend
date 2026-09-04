FROM eclipse-temurin:17-jdk AS build

WORKDIR /workspace

# Copy the build configuration first so Docker can cache dependency resolution.
COPY gradlew settings.gradle.kts build.gradle.kts ./
COPY gradle ./gradle
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

COPY src ./src
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /workspace/build/libs/*.jar app.jar

EXPOSE 8000

ENV JAVA_OPTS=""

# Render supplies PORT at runtime. Keep 8000 as the local/default port.
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -Dserver.port=${PORT:-8000} -jar app.jar"]
