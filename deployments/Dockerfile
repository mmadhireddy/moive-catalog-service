FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD movie-catalog-service-0.0.1-SNAPSHOT.jar movie-catalog-service.jar
RUN sh -c 'touch /movie-catalog-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/movie-catalog-service.jar"]