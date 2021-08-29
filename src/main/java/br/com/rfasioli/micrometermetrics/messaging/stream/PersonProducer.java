package br.com.rfasioli.micrometermetrics.messaging.stream;

import br.com.rfasioli.micrometermetrics.resource.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class PersonProducer implements Supplier<Flux<PersonDTO>> {
  private final Sinks.Many<PersonDTO> sink = Sinks.many().unicast().onBackpressureBuffer();

  @Override
  public Flux<PersonDTO> get() {
    return sink.asFlux();
  }

  public void produce(final PersonDTO personDTO) {
    sink.emitNext(personDTO, Sinks.EmitFailureHandler.FAIL_FAST);
  }
}
