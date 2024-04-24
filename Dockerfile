FROM openjdk:17

COPY build/libs/craflyServer-0.0.1-SNAPSHOT.jar app.jar

ENV TZ=Asia/Seoul

ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app.jar"]