package br.com.rfasioli.micrometermetrics.messaging.stream;

import br.com.rfasioli.micrometermetrics.persistence.repository.PersonRepository;
import br.com.rfasioli.micrometermetrics.resource.dto.PersonDTO;
import br.com.rfasioli.micrometermetrics.resource.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class PersonConsumer implements Consumer<Flux<PersonDTO>> {
  private final PersonMapper personMapper;
  private final PersonRepository personRepository;

  @Override
  public void accept(Flux<PersonDTO> personDTOFlux) {
    personDTOFlux.log()
        .map(personMapper::fromPersonDTO)
        .doOnNext(personRepository::save);
  }
}
