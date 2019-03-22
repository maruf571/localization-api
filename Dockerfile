FROM anapsix/alpine-java
MAINTAINER Maruf Hassan

ARG JAR_FILE
ADD target/${JAR_FILE} app.jar
ENV JAVA_OPTS="-Dspring.profiles.active=dev"
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar