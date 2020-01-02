package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Postal Delivery Type for the Address")
public enum AddressPAFPostalDeliveryType implements LabelValueEnumInterface {
  // @formatter:off
  CARE_OF_PO("CARE PO", "Care Of Post Office"),
  COMMUNITY_MAIL("CMA", "Community Mail Agent"),
  POSTE_RESTANTE("CARE PO", "Poste Restante"),
  COMMUNITY_BAG("CMB", "Community Mail Bag"),
  PRIVATE_BAG("PRIVATE BAG", "Private Mail Bag Service"),
  GPO_BOX("GPO BOX", "General Post Office Box"),
  ROADSIDE_MAIL_BAG("RMB", "Roadside Mail Bag"),
  ROADSIDE_DELIVERY("RSD", "Roadside Delivery"),
  LOCKED_BAG("LOCKED BAG", "Locked Mail Bag Service"),
  ROADSIDE_BOX("RMB", "Roadside Mail Box"),
  MAIL_SERVICE("MS", "Mail Service"),
  ROADSIDE_MAIL("RMS", "Roadside Mail Service"),
  PO_BOX("PO BOX", "Post Office Box"),
  COMMUNITY_AGENT("CPA", "Community Postal Agent");
  // @formatter:on

  private String value;
  private String label;

  AddressPAFPostalDeliveryType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static AddressPAFPostalDeliveryType fromValue(String text) {
    for (AddressPAFPostalDeliveryType b : AddressPAFPostalDeliveryType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }

  @Override
  @JsonIgnore
  public String label() {
    return label;
  }
}
