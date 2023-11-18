package br.com.rfasioli.micrometermetrics.configuration

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MetricsConfiguration {
    @Bean
    fun metricsCommonTags(): MeterRegistryCustomizer<MeterRegistry> =
        MeterRegistryCustomizer { registry: MeterRegistry ->
            registry.config().commonTags("application", "MicrometerMetricsDemo")
        }
}
