FROM openjdk:17

ADD target/BackendApp-dockerfile.jar backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/backend.jar"]