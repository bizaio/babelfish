package io.biza.babelfish.cdr.exceptions;

public class PayloadConversionException  extends Exception {
  public PayloadConversionException(String reason) {
    super(reason);
  }
  private static final long serialVersionUID = 1L;
}