package br.com.rfasioli.micrometermetrics.resource.mapper;

import br.com.rfasioli.micrometermetrics.persistence.document.Person;
import br.com.rfasioli.micrometermetrics.resource.dto.PersonDTO;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface PersonMapper {
  PersonMapper INSTANCE = Mappers.getMapper( PersonMapper.class );

  PersonDTO toPersonDTO(Person person);

  Person fromPersonDTO(PersonDTO dto);

  default String map(ObjectId value) {
    return value != null ? value.toHexString() : null;
  }

  default ObjectId map(String value) {
    return value != null && ObjectId.isValid(value) ? new ObjectId(value) : null;
  }
}
