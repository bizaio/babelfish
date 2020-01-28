/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.interfaces.cdr.banking.product;

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.converter.cdr.UriStringToUriConverter;
import io.biza.babelfish.converter.cdr.UriToUriStringConverter;
import io.biza.babelfish.enumerations.cdr.BankingProductConstraintType;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "Banking Product Constraint Definition")
public interface BankingProductConstraintV1 {
  /**
   * Get the type of constraint using the BankingProductConstraintType enumeration
   * 
   * @return a BankingProductConstraintType enumeration value
   */
  @Schema(description = "The type of constraint described.", required = true)
  @NotNull
  @JsonGetter("constraintType")
  public BankingProductConstraintType getType();

  public default BankingProductConstraintType type() {
    return getType();
  }

  /**
   * Set the type of constraint
   * 
   * @param type of constraint to set
   */
  @JsonSetter("constraintType")
  public void setType(BankingProductConstraintType type);

  public default BankingProductConstraintV1 type(BankingProductConstraintType constraintType) {
    setType(constraintType);
    return this;
  }

  /**
   * Get Additional Value field containing value associated with the specified constraint type.
   * Refer to the Standards for combinations of validity
   * 
   * @return Additional Value String
   */
  @Schema(
      description = "Generic field containing additional information relevant to the [constraintType](#tocSproductconstrainttypedoc) specified.  Whether mandatory or not is dependent on the value of [constraintType](#tocSproductconstrainttypedoc)")
  @JsonGetter("additionalValue")
  public String getAdditionalValue();

  public default String additionalValue() {
    return getAdditionalValue();
  }


  /**
   * Set Additional Value field associated with this constraint type
   * 
   * @param additionalValue with value
   */
  @JsonSetter("additionalValue")
  public void setAdditionalValue(String additionalValue);

  public default BankingProductConstraintV1 additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return this;
  }

  /**
   * Get additional information for the constraint
   * 
   * @return String containing more information for display
   */
  @Schema(description = "Display text providing more information the constraint")
  @JsonGetter("additionalInfo")
  public String getAdditionalInfo();

  public default String additionalInfo() {
    return getAdditionalInfo();
  }

  /**
   * Set the additional information for display regarding the constraint
   * 
   * @param additionalInfo as a String with more information
   */
  @JsonSetter("additionalInfo")
  public void setAdditionalInfo(String additionalInfo);

  public default BankingProductConstraintV1 additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  /**
   * Get additional information web page
   * 
   * @return a URI containing a link to additional information
   */
  @Schema(description = "Link to a web page with more information on the constraint",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("additionalInfoUri")
  public URI getAdditionalInfoUri();

  public default URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }

  /**
   * Set additional information web page
   * 
   * @param additionalInfoUri containing a url to additional information for this constraint
   */
  @JsonSetter("additionalInfoUri")
  public void setAdditionalInfoUri(URI additionalInfoUri);

  public default BankingProductConstraintV1 additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return this;
  }
}
