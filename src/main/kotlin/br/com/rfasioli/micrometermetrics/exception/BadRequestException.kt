package br.com.rfasioli.micrometermetrics.exception

class BadRequestException(label: String?, value: String) :
    RuntimeException("Bad request, wrong parameter $label: [$value]" )
