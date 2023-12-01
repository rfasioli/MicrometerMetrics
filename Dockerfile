FROM amazoncorretto:21-alpine
LABEL authors="rfasioli"

RUN mkdir /opt/app
WORKDIR /opt/app
CMD ["java", "-jar", "/opt/app/app.jar"]

ENV JAVA_TOOL_OPTIONS="-javaagent:/opt/app/opentelemetry-javaagent.jar" \
    OTEL_SERVICE_NAME=micrometermetrics-application \
    OTEL_RESOURCE_ATTRIBUTES=micrometermetrics-application \
    OTEL_TRACES_EXPORTER=otlp \
    OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:4317 \
    OTEL_EXPORTER_OTLP_TRACES_ENDPOINT=http://localhost:4317 \
    OTEL_EXPORTER_OTLP_PROTOCOL=grpc \
    OTEL_EXPORTER_OTLP_TRACES_PROTOCOL=grpc

RUN apk --no-cache add curl
RUN curl -L -O https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar

COPY libs/*.jar /opt/app/app.jar
