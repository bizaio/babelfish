package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Common: Unit of Measure")
public enum CommonUnitOfMeasureType implements LabelValueEnumInterface {
    DOLLAR("DOLLAR", "Dollar Value"),
    PERCENT("PERCENT", "Percentage Value"),
    MONTH("MONTH", "Month"),
    DAY("DAY", "Day of Month");
    
    private String value;
    private String label;

    CommonUnitOfMeasureType(String value, String label) {
      this.value = value;
      this.label = label;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CommonUnitOfMeasureType fromValue(String text) {
      for (CommonUnitOfMeasureType b : CommonUnitOfMeasureType.values()) {
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