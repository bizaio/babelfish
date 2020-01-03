/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@BabelFishModel(description = "Authorised Entity details")
public interface BankingAuthorisedEntity {

  @BabelFishModelProperty(
      description = "Description of the authorised entity derived from previously executed direct debits")
  @JsonGetter("description")
  public String getDescription();

  @JsonSetter("description")
  public void setDescription(String description);

  public default BankingAuthorisedEntity description(String description) {
    setDescription(description);
    return this;
  }

  @BabelFishModelProperty(
      description = "Name of the financial institution through which the direct debit will be executed. Is required unless the payment is made via a credit card scheme")
  @JsonGetter("financialInstitution")
  public String getFinancialInstitution();

  @JsonSetter("financialInstitution")
  public void setFinancialInstitution(String financialInstitution);

  public default BankingAuthorisedEntity financialInstitution(String financialInstitution) {
    setFinancialInstitution(financialInstitution);
    return this;
  }

  @BabelFishModelProperty(description = "Australian Business Number for the authorised entity")
  @JsonGetter("abn")
  public String getAbn();

  @JsonSetter("abn")
  public void setAbn(String abn);

  public default BankingAuthorisedEntity abn(String abn) {
    setAbn(abn);
    return this;
  }

  @BabelFishModelProperty(description = "Australian Company Number for the authorised entity")
  @JsonGetter("acn")
  public String getAcn();

  @JsonSetter("acn")
  public void setAcn(String acn);

  public default BankingAuthorisedEntity acn(String acn) {
    setAcn(acn);
    return this;
  }


  @BabelFishModelProperty(
      description = "Australian Registered Body Number for the authorised entity")
  @JsonGetter("arbn")
  public String getArbn();

  @JsonSetter("arbn")
  public void setArbn(String arbn);

  public default BankingAuthorisedEntity arbn(String arbn) {
    setArbn(arbn);
    return this;
  }
}
