package io.biza.babelfish.cdr.exceptions;

public class UnsupportedPayloadException  extends Exception {
  public UnsupportedPayloadException(String reason) {
    super(reason);
  }
  private static final long serialVersionUID = 1L;
}