# Start with base image containing the JAVA runtime
FROM openjdk:21
COPY target/designation-service-0.0.1-SNAPSHOT.jar designation.jar
ENTRYPOINT [ "java", "-jar" , "designation.jar" ]