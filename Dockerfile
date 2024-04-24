FROM openjdk:17

COPY build/libs/pcot-gateway-0.0.1-SNAPSHOT.jar app.jar

ENV TZ=Asia/Seoul

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]