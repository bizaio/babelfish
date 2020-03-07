package io.biza.babelfish.cdr.enumerations.oidc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.babelfish.cdr.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Scopes used within the CDR", enumAsRef = true)
public enum CDRScope implements LabelValueEnumInterface {
  // @formatter:off
  OPENID("openid", "openid"),
  PROFILE("profile", "profile"),
  BANK_ACCOUNTS_BASIC_READ("bank:accounts.basic:read", "bank:accounts.basic:read"),
  BANK_ACCOUNTS_DETAIL_READ("bank:accounts.detail:read", "bank:accounts.detail:read"),
  BANK_TRANSACTIONS_READ("bank:transactions:read", "bank:transactions:read"),
  BANK_PAYEES_READ("bank:payees:read", "bank:payees:read"),
  BANK_REGULAR_PAYMENTS_READ("bank:regular_payments:read", "bank:regular_payments:read"),
  COMMON_CUSTOMER_BASIC_READ("common:customer.basic:read", "common:customer.basic:read"),
  COMMON_CUSTOMER_DETAIL_READ("common:customer.detail:read", "common:customer.detail:read"),
  CDR_REGISTRATION("cdr:registration", "cdr:registration");
  // @formatter:on

  private String value;

  private String label;

  CDRScope(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CDRScope fromValue(String text)
      throws LabelValueEnumValueNotSupportedException {
    for (CDRScope b : CDRScope.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    throw new LabelValueEnumValueNotSupportedException(
        "Unable to identify value of RegisterScope from " + text,
        CDRScope.class.getSimpleName(), CDRScope.values(),
        text);
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}