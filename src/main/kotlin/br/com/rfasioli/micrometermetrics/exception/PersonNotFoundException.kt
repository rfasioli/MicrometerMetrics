package br.com.rfasioli.micrometermetrics.exception

class PersonNotFoundException(id: String) :
    RuntimeException("Person not found for id[$id]")
