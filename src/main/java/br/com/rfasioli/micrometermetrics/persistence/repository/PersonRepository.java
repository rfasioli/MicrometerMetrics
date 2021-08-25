package br.com.rfasioli.micrometermetrics.persistence.repository;

import br.com.rfasioli.micrometermetrics.persistence.document.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, ObjectId> {
}
