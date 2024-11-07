# Extract project name and version
PROJECT_NAME=$(mvn help:evaluate -Dexpression=project.name -q -DforceStdout)
PROJECT_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

# Build the Docker image with extracted name and version
docker build -t "${PROJECT_NAME}:${PROJECT_VERSION}" .