package br.com.rfasioli.micrometermetrics.resource.controller;

import br.com.rfasioli.micrometermetrics.exception.BadRequestException;
import br.com.rfasioli.micrometermetrics.exception.PersonNotFoundException;
import br.com.rfasioli.micrometermetrics.persistence.document.Person;
import br.com.rfasioli.micrometermetrics.persistence.repository.PersonRepository;
import br.com.rfasioli.micrometermetrics.resource.dto.PersonDTO;
import br.com.rfasioli.micrometermetrics.resource.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonController {
  private final PersonRepository personRepository;
  private final PersonMapper personMapper;

  @GetMapping
  public List<PersonDTO> findAll() {
    return personRepository.findAll().stream()
        .map(personMapper::toPersonDTO)
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public PersonDTO findById(@PathVariable String id) {
    if (!ObjectId.isValid(id)) {
      throw new BadRequestException("id", id);
    }
    return personRepository.findById(new ObjectId(id))
        .map(personMapper::toPersonDTO)
        .orElseThrow(()-> new PersonNotFoundException(id));
  }

  @PostMapping
  public PersonDTO save(@RequestBody PersonDTO dto) {
    final var person = personRepository.save(personMapper.fromPersonDTO(dto));
    return personMapper.toPersonDTO(person);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    if (!ObjectId.isValid(id)) {
      throw new BadRequestException("id", id);
    }
    personRepository.deleteById(new ObjectId(id));
  }
}
