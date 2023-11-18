package br.com.rfasioli.micrometermetrics.worker

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.Random
import java.util.concurrent.atomic.AtomicInteger

@Component
class Scheduler(meterRegistry: MeterRegistry) {
    private var testGauge: AtomicInteger? = null
    private lateinit var testCounter: Counter

    init {
        // Counter vs. gauge, summary vs. histogram
        // https://prometheus.io/docs/practices/instrumentation/#counter-vs-gauge-summary-vs-histogram
        testGauge = meterRegistry.gauge("custom_gauge", AtomicInteger(0))
        testCounter = meterRegistry.counter("custom_counter")
    }

    @Scheduled(fixedRateString = "1000", initialDelayString = "0")
    fun schedulingTask() {
        testGauge!!.set(getRandomNumberInRange(0, 100))
        testCounter.increment()
    }

    companion object {
        private fun getRandomNumberInRange(min: Int, max: Int): Int {
            require(min < max) { "max must be greater than min" }
            val r = Random()
            return r.nextInt(max - min + 1) + min
        }
    }
}
