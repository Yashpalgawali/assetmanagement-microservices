FROM openjdk:21
EXPOSE 8761
COPY target/*.jar naming-server.jar
ENTRYPOINT [ "java", "-jar" ,"naming-server.jar"]