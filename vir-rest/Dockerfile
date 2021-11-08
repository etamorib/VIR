FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/rest/src
COPY pom.xml /home/rest
RUN mvn -f /home/rest/pom.xml clean package

FROM openjdk:11
MAINTAINER bm.vir.com
COPY --from=build home/rest/target/vir-rest-0.0.1-SNAPSHOT.jar vir-rest-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/vir-rest-0.0.1.jar"]