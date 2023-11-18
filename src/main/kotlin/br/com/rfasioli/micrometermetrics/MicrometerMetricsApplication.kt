package br.com.rfasioli.micrometermetrics

import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MicrometerMetricsApplication {
    @Bean
    fun threadMetrics() = JvmThreadMetrics()
}

fun main(args: Array<String>) {
    runApplication<MicrometerMetricsApplication>(*args)
}