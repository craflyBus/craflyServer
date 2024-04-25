FROM openjdk:17

COPY build/libs/craflyServer-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ \
    "java", \
    "-Dspring.config.location=classpath:/,file:/environment/key.yaml", \
    "-jar", \
    "/app.jar" \
]