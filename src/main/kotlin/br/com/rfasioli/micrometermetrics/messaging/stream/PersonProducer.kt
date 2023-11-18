package br.com.rfasioli.micrometermetrics.messaging.stream

import br.com.rfasioli.micrometermetrics.resource.dto.PersonDTO
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.util.function.Supplier

@Component
class PersonProducer : Supplier<Flux<PersonDTO>> {
    private val sink = Sinks.many().unicast().onBackpressureBuffer<PersonDTO>()

    override fun get() = sink.asFlux()

    fun produce(personDTO: PersonDTO) {
        sink.tryEmitNext(personDTO).orThrow()
    }
}
