FROM openjdk:8-jre

MAINTAINER akiraLiu <liuchang8939@126.com>

ENV APP_VERSION 1.0-SNAPSHOT

ENV DOCKERIZE_VERSION v0.6.1

ENV APP_OPTIONS ""

RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

RUN mkdir /app
WORKDIR /app
ADD spring-cloud-greenwich-eureka-1.0-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
EXPOSE 9002
EXPOSE 9003
