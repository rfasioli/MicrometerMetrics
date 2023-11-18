package br.com.rfasioli.micrometermetrics.messaging.stream

import br.com.rfasioli.micrometermetrics.persistence.document.Person
import br.com.rfasioli.micrometermetrics.persistence.repository.PersonRepository
import br.com.rfasioli.micrometermetrics.resource.dto.PersonDTO
import br.com.rfasioli.micrometermetrics.resource.mapper.toPerson
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.util.function.Consumer

@Component
class PersonConsumer(
    private val personRepository: PersonRepository
) : Consumer<Flux<PersonDTO>> {
    override fun accept(personDTOFlux: Flux<PersonDTO>) {
        personDTOFlux.log()
            .map(PersonDTO::toPerson)
            .doOnNext(personRepository::save)
    }
}
