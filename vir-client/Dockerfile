FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/client/src
COPY pom.xml /home/client
RUN mvn -f /home/client/pom.xml clean package


FROM openjdk:11
MAINTAINER bm.vir.com
COPY --from=build home/client/target/vir-client-1.0-SNAPSHOT.jar vir-client-1.0.jar
ENTRYPOINT ["java", "-jar", "/vir-client-1.0.jar"]