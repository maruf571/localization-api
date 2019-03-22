FROM anapsix/alpine-java
MAINTAINER Maruf Hassan

ENV JAVA_OPTS="-Dspring.profiles.active=dev"
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/myservice.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/myservice/myservice.jar
