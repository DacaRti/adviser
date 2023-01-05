FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=/home/app/target/ROOT.jar
WORKDIR /opt/app
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
