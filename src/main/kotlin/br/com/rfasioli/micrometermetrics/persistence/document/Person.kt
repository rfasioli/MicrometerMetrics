package br.com.rfasioli.micrometermetrics.persistence.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Person (
    @Id val id: ObjectId? = null,
    val firstName: String,
    val sureName: String? = null,
    val lastName: String
)
