FROM eclipse-temurin:21
RUN mkdir /opt/app
COPY target/docker-0.0.1-SNAPSHOT.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]
EXPOSE 8080

#build image
#docker build -t japp .

#run image
#docker run -it --rm japp