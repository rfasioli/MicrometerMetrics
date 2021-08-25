package br.com.rfasioli.micrometermetrics.exception;

public class PersonNotFoundException extends RuntimeException {
  private static final String MESSAGE = "e=Person not found for id[{}]";

  public PersonNotFoundException(String id) {
    super(String.format(MESSAGE, id));
  }
}
