FROM java:8
MAINTAINER keryhu@hotmail.com
VOLUME /tmp
LABEL can-autoremove="true"
EXPOSE 8080
ADD spring-boot-0.0.1-SNAPSHOT.jar /app.jar
RUN bash -c 'touch  /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
