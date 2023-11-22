FROM amazoncorretto:21-alpine
LABEL authors="rfasioli"
RUN mkdir /opt/app
CMD ["java", "-jar", "/opt/app/app.jar"]
COPY *.jar /opt/app/app.jar
