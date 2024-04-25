FROM openjdk:17

COPY build/libs/craflyServer-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ \
    "java", \
    "-Dspring.profiles.active=dev", \
    "-Dspring.config.location=environment/key.yaml", \
    "-jar", \
    "/app.jar" \
]