# MicrometerMetrics
Projeto testes de geração de métricas.

## Tecnologias
- Java 17
- Kotlin 1.8.22
- Spring Boot 2.5.4
- Open API
- Micrometer
- Open Telemetry 1.32
- Open Telemetry Collector 0.90.1
- Prometheus 2.48
- Jaeger 1.51
- Zikpin
- Docker
- MongoDB

## Executando a aplicação
Executar as dependências:
``` bash
docker-compose up -d
```
Executar a aplicação:
``` bash
## Environments
JAVA_TOOL_OPTIONS=-javaagent:./config/otelcollector/agent/opentelemetry-javaagent.jar
OTEL_SERVICE_NAME=micrometermetrics-application
OTEL_LOGS_EXPORTER=otlp

OTEL_EXPORTER_OTLP_METRICS_TEMPORALITY_PREFERENCE=cumulative
OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:4317
OTEL_LOGS_EXPORTER=otlp
OTEL_RESOURCE_ATTRIBUTES=service.namespace=micrometermetrics-application

OTEL_LOGS_EXPORTER=otlp|zipkin|logging
OTEL_METRICS_EXPORTER=logging
OTEL_LOGS_EXPORTER=logging

## Executando
./gradlew bootRun
```

## URLs expostas pela aplicação e dependências
- Doc da aplicação:
  - http://localhost:8080/v3/api-docs
  - http://localhost:8080/swagger-ui.html
- Prometheus:
  - http://localhost:9090
- Grafana:
  - http://localhost:3000
    - Usuário e senha padrão: admin/admin
- Jaeger:
  - http://localhost:16686
- RabbitMQ
  - http://localhost:5672
  - http://localhost:15672
    - guest/guest
- MongoDB
  - http://localhost:27017
    - username: root
    - password: admin123 
    - database: test_db
- MongoExpress
  - http://localhost:8081
  
- Serviços da aplicação:
  - GET http://localhost:8080/sample
  - POST http://localhost:8080/sample
  - GET http://localhost:8080/sample/property [Redirect: /sample/asset]
  - GET http://localhost:8080/sample/asset [Internal Server Error 500]

## Configurando o Grafana
1) Acessar o grafana pelo navegador com a url: http://localhost:3000
2) Efetuar o login com o usuário e senha padrão: admin / admin
3) Configurar o data source para o prometheus:
![Data Source](./config/images/grafana_datasource.png)
4) Importar um dashboard 

### Alguns Dashboards
[JVM (Micrometer)](https://grafana.com/grafana/dashboards/4701)   
[Spring Boot Statistics](https://grafana.com/grafana/dashboards/6756)
[Custom Dashboard](./src/main/resources/grafana/CustomDashboard.json)

## Roadmap
- Monitorar Spring Feign Client
### WIP
- Monitorar Spring Cloud Stream
- Gerar dados de monitoração customizados

# Referências
- https://www.jaegertracing.io/docs/1.51/
- https://prometheus.io/docs/prometheus/latest/
- https://opentelemetry.io/docs/collector/
- https://opentelemetry.io/docs/instrumentation/java/
- https://opentelemetry.io/docs/instrumentation/java/automatic/spring-boot/
- 
- https://opentelemetry.io/docs/demo/
- https://medium.com/@zzzzzYang/implement-opentelemetry-to-export-data-to-jaeger-prometheus-and-grafana-1098352370c0
- https://medium.com/projuristech/monitorando-uma-aplica%C3%A7%C3%A3o-spring-boot-2-x-cef826ae793c
- https://www.baeldung.com/micrometer
- https://www.mokkapps.de/blog/monitoring-spring-boot-application-with-micrometer-prometheus-and-grafana-using-custom-metrics/
- https://prometheus.io/docs/prometheus/latest/querying/examples/
- https://cloud.spring.io/spring-cloud-stream/reference/html/spring-cloud-stream.html
- https://www.baeldung.com/spring-cloud-stream
- https://github.com/palantir/gradle-docker
- https://github.com/school-digital-agenda/sda-bootstrap/blob/main/README.md
