FROM amazoncorretto:21-alpine
LABEL authors="rfasioli"

RUN mkdir /opt/app
WORKDIR /opt/app
CMD ["java", "-jar", "/opt/app/app.jar"]

ENV JAVA_TOOL_OPTIONS="-javaagent:/opt/app/opentelemetry-javaagent.jar"
ENV OTEL_EXPORTER=jaeger
ENV OTEL_EXPORTER_JAEGER_SERVICE_NAME=micrometermetrics-application
ENV OTEL_EXPORTER_JAEGER_ENDPOINT=http://localhost:14250/api/traces
ENV OTEL_TRACES_EXPORTER=logging
ENV OTEL_METRICS_EXPORTER=logging
ENV OTEL_LOGS_EXPORTER=logging

RUN apk --no-cache add curl
RUN curl -L -O https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar

COPY libs/*.jar /opt/app/app.jar
