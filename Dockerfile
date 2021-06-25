#FROM amazoncorretto:11-alpine-jdk
FROM adoptopenjdk/openjdk16
COPY target/testSpringBoot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]