FROM arm64v8/openjdk:11-jre
ARG JAR_FILE=target/*.jar
COPY target/scraper-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
