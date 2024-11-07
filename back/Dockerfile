# Stage 1: Maven Build
FROM maven:3.9.9-eclipse-temurin-21 AS maven_builder
WORKDIR /build
COPY ./pom.xml ./
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean package -DskipTests

# Stage 2: Spring Boot Layertools Build
FROM eclipse-temurin:21 as builder
WORKDIR /build
COPY --from=maven_builder /build/target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# Stage 3: Final Image
FROM eclipse-temurin:21
WORKDIR /app
COPY --from=builder /build/dependencies/ ./
COPY --from=builder /build/snapshot-dependencies/ ./
COPY --from=builder /build/spring-boot-loader/ ./
COPY --from=builder /build/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]