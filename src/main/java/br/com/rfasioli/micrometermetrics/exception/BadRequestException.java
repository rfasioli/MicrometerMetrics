package br.com.rfasioli.micrometermetrics.exception;

public class BadRequestException extends RuntimeException {
  private static final String MESSAGE = "Bad request, wrong parameter {}: [{}]";

  public BadRequestException(final String label, final String value) {
    super(String.format(MESSAGE, label, value));
  }
}
