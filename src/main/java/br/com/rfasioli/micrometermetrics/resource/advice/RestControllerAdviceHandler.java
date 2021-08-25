package br.com.rfasioli.micrometermetrics.resource.advice;

import br.com.rfasioli.micrometermetrics.exception.BadRequestException;
import br.com.rfasioli.micrometermetrics.exception.PersonNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class RestControllerAdviceHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ PersonNotFoundException.class })
  protected ResponseEntity<Object> handlePersonNotFoundException(PersonNotFoundException ex,
                                                                 WebRequest request) {
    return handleExceptionInternal(ex,
        ex.getMessage(),
        new HttpHeaders(),
        HttpStatus.NO_CONTENT,
        request);
  }

  @ExceptionHandler({ BadRequestException.class })
  protected ResponseEntity<Object> handlePersonNotFoundException(BadRequestException ex,
                                                                 WebRequest request) {
    return handleExceptionInternal(ex,
        ex.getMessage(),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST,
        request);
  }
}