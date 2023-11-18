package br.com.rfasioli.micrometermetrics.resource.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data

data class PersonDTO (
    val id: String? = null,
    val firstName: String,
    val sureName: String? = null,
    val lastName: String
)
