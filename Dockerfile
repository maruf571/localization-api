FROM anapsix/alpine-java

MAINTAINER Maruf Hassan

VOLUME /tmp
ADD target/localization-api*.jar app.jar
ENV JAVA_OPTS="-Dspring.profiles.active=dev"
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar