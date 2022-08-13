FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /home
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]