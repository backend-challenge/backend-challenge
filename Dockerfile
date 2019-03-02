FROM openjdk:8
ADD target/docker-store-service.jar docker-store-service.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "docker-store-service.jar"]