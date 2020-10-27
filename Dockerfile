FROM openjdk:11-jdk-alpine

EXPOSE 8081

ARG JAR_FILE=build/libs/communicator-0.0.1-SNAPSHOT
ADD ${JAR_FILE} communicator.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","communicator.jar"]
