FROM openjdk:17

ADD /target/BackendApp-0.0.1-SNAPSHOT.jar backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "backend.jar"]