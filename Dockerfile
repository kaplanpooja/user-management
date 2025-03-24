FROM registry.access.redhat.com/ubi8/openjdk-21:1.20

ENV LANGUAGE='en_US:en'

# Set the working directory in container
WORKDIR /app

COPY target/user-management-1.0.0-SNAPSHOT-runner.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]

EXPOSE 8080

# docker build -f src/main/docker/Dockerfile -t user-management:latest .
#docker build -t user-management:latest .
# docker run -p 8080:8080 user-management:latest
