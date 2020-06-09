package io.biza.babelfish.common.exceptions;

public class PayloadConversionException  extends Exception {
  public PayloadConversionException(String reason) {
    super(reason);
  }
  private static final long serialVersionUID = 1L;
}