package io.biza.babelfish.common.exceptions;

public class UnsupportedVersionException  extends Exception {
  public UnsupportedVersionException(String reason) {
    super(reason);
  }
  private static final long serialVersionUID = 1L;
}