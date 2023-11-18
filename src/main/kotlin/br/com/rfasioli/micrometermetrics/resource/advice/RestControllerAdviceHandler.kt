package br.com.rfasioli.micrometermetrics.resource.advice

import br.com.rfasioli.micrometermetrics.exception.BadRequestException
import br.com.rfasioli.micrometermetrics.exception.PersonNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
internal class RestControllerAdviceHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(PersonNotFoundException::class)
    protected fun handlePersonNotFoundException(
        ex: PersonNotFoundException,
        request: WebRequest
    ) = handleExceptionInternal(
            ex,
            ex.localizedMessage,
            HttpHeaders(),
            HttpStatus.NOT_FOUND,
            request
        )

    @ExceptionHandler(BadRequestException::class)
    protected fun handleBadRequestException(
        ex: BadRequestException,
        request: WebRequest
    ) = handleExceptionInternal(
            ex,
            ex.localizedMessage,
            HttpHeaders(),
            HttpStatus.BAD_REQUEST,
            request
        )
}
