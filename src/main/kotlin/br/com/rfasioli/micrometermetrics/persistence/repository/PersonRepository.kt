package br.com.rfasioli.micrometermetrics.persistence.repository

import br.com.rfasioli.micrometermetrics.persistence.document.Person
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PersonRepository : MongoRepository<Person, ObjectId>
