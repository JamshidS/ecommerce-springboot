FROM openjdk:17-jdk

WORKDIR /app

COPY build/libs/*.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]