package br.com.rfasioli.micrometermetrics.resource.mapper;

import br.com.rfasioli.micrometermetrics.persistence.document.Person
import br.com.rfasioli.micrometermetrics.resource.dto.PersonDTO
import org.bson.types.ObjectId

fun Person.toPersonDTO() =
    PersonDTO(
        id = this.id?.toHexString(),
        firstName = this.firstName,
        sureName = this.sureName,
        lastName = this.lastName,
    )

fun PersonDTO.toPerson() =
    Person(
        id = if (this.id != null && ObjectId.isValid(this.id)) ObjectId(this.id) else null,
        firstName = this.firstName,
        sureName = this.sureName,
        lastName = this.lastName,
    )
