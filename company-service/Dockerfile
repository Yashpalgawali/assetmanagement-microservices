#FROM openjdk:21
#COPY target/*.jar company-service.jar
#EXPOSE 7001
#ENTRYPOINT [ "java", "-jar", "company-service.jar" ]

FROM  maven:3.8.8-eclipse-temurin-21 AS build
WORKDIR /home/app
COPY . /home/app/
RUN mvn clean package


FROM openjdk:21
VOLUME [ "/tmp" ]
COPY --from=build /home/app/target/*.jar company-service.jar
EXPOSE 7001
ENTRYPOINT [ "java", "-jar", "company-service.jar" ]