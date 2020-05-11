package io.biza.babelfish.common.exceptions;

import io.biza.babelfish.common.interfaces.LabelValueEnumInterface;

public class LabelValueEnumValueNotSupportedException extends Exception {

  protected LabelValueEnumInterface[] labelValues;
  private String className;
  private String suppliedValue;

  public LabelValueEnumValueNotSupportedException(String reason, String className,
      LabelValueEnumInterface[] labelValues, String suppliedValue) {
    super(reason);
    this.className = className;
    this.labelValues = labelValues;
    this.suppliedValue = suppliedValue;
  }

  public LabelValueEnumInterface[] getSourceEnum() {
    return labelValues;
  }

  public String getClassName() {
    return className;
  }

  public String getSuppliedValue() {
    return suppliedValue;
  }

  private static final long serialVersionUID = 1L;
}
