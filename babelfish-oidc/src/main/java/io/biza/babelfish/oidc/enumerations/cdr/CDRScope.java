package io.biza.babelfish.oidc.enumerations.cdr;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.biza.babelfish.common.exceptions.LabelValueEnumValueNotSupportedException;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Scopes used within the CDR", enumAsRef = true)
public enum CDRScope {
  // @formatter:off
  BANK_ACCOUNTS_BASIC_READ("bank:accounts.basic:read", "Account name, type and balance", List.of("Name of account", "Type of account", "Account Balance")),
  BANK_ACCOUNTS_DETAIL_READ("bank:accounts.detail:read", "Account numbers and features", List.of("Account Number", "Interest Rates", "Fees", "Discounts", "Account Terms", "Account Mail Address")),
  BANK_TRANSACTIONS_READ("bank:transactions:read", "Transaction Details", List.of("Incoming and Outgoing Transactions", "Amounts", "Dates", "Descriptions of trannsactions", "Who you have sent money to and received money from; (eg. their name)")),
  BANK_PAYEES_READ("bank:payees:read", "Saved Payees", List.of("Names and details of accounts you have saved (eg. their BSB and Account Number, BPay CRN and Biller code, or NPP PayID")),
  BANK_REGULAR_PAYMENTS_READ("bank:regular_payments:read", "Direct debits and scheduled payments", List.of("Direct debits", "Scheduled Payments")),
  COMMON_CUSTOMER_BASIC_READ("common:customer.basic:read", "Name and occupation", List.of("Name", "Occupation")),
  COMMON_CUSTOMER_DETAIL_READ("common:customer.detail:read", "Contact Details", List.of("Phone", "Email Address", "Mail Address", "Residential Address")),
  CDR_REGISTRATION("cdr:registration", "Dynamic Client Registration", List.of("Client Management Access"));
  // @formatter:on

  private String value;
  private String title;
  private List<String> permissionLanguage;

  CDRScope(String value, String title, List<String> permissionLanguage) {
    this.value = value;
    this.title = title;
    this.permissionLanguage = permissionLanguage;
  }
  
  public String title() {
	  return this.title;
  }
  
  public List<String> permissionLanguage() {
	  return this.permissionLanguage;
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