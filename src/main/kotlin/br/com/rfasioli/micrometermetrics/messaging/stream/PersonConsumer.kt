package br.com.rfasioli.micrometermetrics.messaging.stream

import br.com.rfasioli.micrometermetrics.persistence.repository.PersonRepository
import br.com.rfasioli.micrometermetrics.resource.dto.PersonDTO
import br.com.rfasioli.micrometermetrics.resource.mapper.toPerson
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.SignalType
import java.util.function.Consumer
import java.util.logging.Level

@Component("person-consumer")
class PersonConsumer(
    private val personRepository: PersonRepository
) : Consumer<Flux<PersonDTO>> {
    override fun accept(personDTOS: Flux<PersonDTO>) {
        personDTOS
            .map { it.toPerson() }
            .doOnNext { personRepository.save(it) }
            .log("PersonConsumer", Level.INFO, SignalType.ON_NEXT, SignalType.ON_COMPLETE)
            .log("PersonConsumer", Level.WARNING, SignalType.ON_ERROR)
            .subscribe()
    }
}
