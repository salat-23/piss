FROM openjdk:18-alpine
WORKDIR /var/app
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
