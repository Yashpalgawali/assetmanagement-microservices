# Start with base image containing the JAVA runtime
FROM openjdk:21
EXPOSE 9200
COPY target/*.jar designation-service.jar
ENTRYPOINT [ "java", "-jar" , "designation-service.jar" ]