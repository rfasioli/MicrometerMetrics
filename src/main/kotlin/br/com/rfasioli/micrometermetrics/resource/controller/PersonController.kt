package br.com.rfasioli.micrometermetrics.resource.controller

import br.com.rfasioli.micrometermetrics.exception.BadRequestException
import br.com.rfasioli.micrometermetrics.exception.PersonNotFoundException
import br.com.rfasioli.micrometermetrics.messaging.stream.PersonProducer
import br.com.rfasioli.micrometermetrics.persistence.repository.PersonRepository
import br.com.rfasioli.micrometermetrics.resource.dto.PersonDTO
import br.com.rfasioli.micrometermetrics.resource.mapper.toPerson
import br.com.rfasioli.micrometermetrics.resource.mapper.toPersonDTO
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("person")
class PersonController(
    private val personRepository: PersonRepository,
    private val personProducer: PersonProducer
) {
    @GetMapping
    fun findAll(): List<PersonDTO> =
        personRepository.findAll()
            .mapNotNull { it.toPersonDTO() }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): PersonDTO =
        if (ObjectId.isValid(id)) {
            personRepository.findById(ObjectId(id))
                .orElseThrow { PersonNotFoundException(id) }
                .toPersonDTO()
        } else {
            throw BadRequestException("id", id)
        }
    
    @PostMapping
    fun save(@RequestBody dto: PersonDTO): PersonDTO =
        personRepository.save(dto.toPerson())
            .toPersonDTO()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        if (!ObjectId.isValid(id)) {
            throw BadRequestException("id", id)
        }
        personRepository.deleteById(ObjectId(id))
    }

    @PostMapping("/produce")
    fun produce(@RequestBody dto: PersonDTO) {
        personProducer.produce(dto)
    }
}
