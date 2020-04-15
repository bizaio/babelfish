package io.biza.babelfish.cdr.enumerations.oidc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.babelfish.cdr.exceptions.LabelValueEnumValueNotSupportedException;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Scopes used within the CDR", enumAsRef = true)
public enum CDRScope {
  // @formatter:off
  OPENID("openid"),
  PROFILE("profile"),
  BANK_ACCOUNTS_BASIC_READ("bank:accounts.basic:read"),
  BANK_ACCOUNTS_DETAIL_READ("bank:accounts.detail:read"),
  BANK_TRANSACTIONS_READ("bank:transactions:read"),
  BANK_PAYEES_READ("bank:payees:read"),
  BANK_REGULAR_PAYMENTS_READ("bank:regular_payments:read"),
  COMMON_CUSTOMER_BASIC_READ("common:customer.basic:read"),
  COMMON_CUSTOMER_DETAIL_READ("common:customer.detail:read"),
  CDR_REGISTRATION("cdr:registration");
  // @formatter:on

  private String value;

  CDRScope(String value) {
    this.value = value;
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
    return null;
  }
}