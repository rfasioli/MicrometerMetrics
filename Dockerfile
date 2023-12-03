FROM gradle:8-jdk17 AS builder

WORKDIR /usr/src/app/

COPY ./src/ ./
RUN gradle assemble bootJar

# ------------------------------------

FROM amazoncorretto:21-alpine
LABEL authors="rfasioli"

RUN mkdir /opt/app
WORKDIR /opt/app
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
ENV JAVA_TOOL_OPTIONS=-javaagent:/app/opentelemetry-javaagent.jar

COPY --from=builder /usr/src/app/build/libs/*.jar ./app.jar
