package io.biza.babelfish.cdr.enumerations.register;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.biza.babelfish.common.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.common.interfaces.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Certificate Status", enumAsRef = true)
public enum CertificateStatus implements LabelValueEnumInterface {
  // @formatter:off
  ACTIVE("ACTIVE", "Active");
  // @formatter:on

  private String value;

  private String label;

  CertificateStatus(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CertificateStatus fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (CertificateStatus b : CertificateStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of CertificateStatus from " + text,
        CertificateStatus.class.getSimpleName(), CertificateStatus.values(),
        text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}