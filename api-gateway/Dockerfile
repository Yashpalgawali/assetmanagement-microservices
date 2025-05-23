FROM openjdk:21
EXPOSE 8765
COPY target/*.jar api-gateway.jar
ENTRYPOINT [ "java", "-jar" ,"api-gateway.jar"]