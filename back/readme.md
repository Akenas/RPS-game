# Backend Application

The backend application provides most of the logical capabilities of the application. It has been developed with Spring Boot 3 as the main framework and designed based on hexagonal architecture.

## Project Structure

```
├── app
├── domain
│   ├── model
│   │   ├── game
│   │   │   ├── players
│   │   │   └── ruleset
│   │   └── users
│   ├── repositories
│   └── transients
│       ├── events
│       │   ├── game
│       │   └── queues
│       └── users
├── infrastructure
│   ├── config
│   ├── controller
│   │   └── websockets
│   └── persistence
│       └── jpa
│           ├── entities
│           ├── mappers
│           └── repositories
└── service
    ├── game
    │   └── events
    ├── queues
    └── security
        └── jwt
```


- **app**: Contains the main class and application entry point .
- **domain**:
    - **model**: Contains the core classes that make things work. Expect some logic here.
    - **repositories**: Interfaces for data management.
    - **transients**: Requests, events , and DTOs go here .
- **infrastructure**:
    - **config**: Contains the configuration classes that are used by Spring Boot to wire services, controllers, etc.
    - **controller**: This folder contains HTTP RestControllers and websocket management endpoints .
    - **persistence**: Classes for persistence management. In this case , it contains JPA entities, mappers , and repositories.
- **service**:
    - **game**: Contains the classes used for game logic and specific use cases .
    - **queues**: Contains the classes for game queue management so we can match players .
    - **security**: Classes for security management such as JWT token creation and validation .

## Features

- **User Management**: The application is capable of signing up and logging in users. When a client registers with the application , it stores the user data and creates a "Player" object for them.
- **Game & Queue Management**: The application manages all the game data and computation on the backend . Therefore, if a user manages to make changes on the frontend and tries to cheat , it usually won't work.
- **Websockets**: Apart from the usual REST controllers to retrieve data, the application has been built around websockets for bidirectional, real-time communication.
- **JWT Security**: When the client logs in, the application provides a JWT token for further authentication when calling the REST controllers and connecting to the websocket game feature .
- **Data Persistence**: Data is stored in a MySQL database .
## Building and Running the Project

We provide two methods for building and running the project. In both cases , the application expects a MySQL instance configured to run with port 3306 open. If you do not have one, the project will not start.
**Note**: If you do not have a MySQL instance installed, check the Prerequisites section of this file.

### Building the Project with Maven

From the root folder of the project, run the following commands:
```bash
cd back
mvn clean package
cd target
java -jar rps-game-manager.jar
```
This should run the available tests, compile the application , generate a JAR file in the /target folder , and run the application.

### Building the Project with Docker

We have packaged a Dockerfile in the root folder of the backend service (/back). If you have Docker installed, you can run:
```bash
cd back
docker build ./ -t rps-game-manager
docker run --network="host" rps-game-manager
```
The Dockerfile looks like this :
```docker
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
```
This will create the JAR , extract the layers , and generate a layered image for Docker.

## Known Bugs as of November 13, 2024

- When making a request with an invalid JWT token, the system detects that it is invalid but returns a 200 OK status with an empty body instead of the common 401. This is related to Spring Security .

## TODOs

- Create a controller to retrieve game match data for a player
- Split the service to create GameService and UserService with two different databases for security in case of a breach.