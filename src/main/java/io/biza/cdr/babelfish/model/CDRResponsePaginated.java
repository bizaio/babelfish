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
package io.biza.cdr.babelfish.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.model.common.CommonCurrencyAmount;
import io.biza.cdr.babelfish.model.common.CommonDiscoveryOutage;
import io.biza.cdr.babelfish.model.common.Links;
import io.biza.cdr.babelfish.model.common.LinksPaginated;
import io.biza.cdr.babelfish.model.common.Meta;
import io.biza.cdr.babelfish.model.common.MetaPaginated;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.model.common.CommonDiscoveryStatusData;
import io.biza.cdr.babelfish.v1.response.ResponseCommonDiscoveryStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@BabelFishModel(
    description = "This is a Paginated CDR Response")
@Valid
public abstract class CDRResponsePaginated {
  @BabelFishModelProperty(description = "The Links Object", required = true)
  @JsonProperty("links")
  @NotNull
  LinksPaginated links;
  
  @BabelFishModelProperty(
      description = "The meta object is used to provide additional information such as second factor authorisation data, traffic management, pagination counts or other purposes that are complementary to the workings of the API.",
      required = true, attributeName = "meta")
  @JsonProperty("meta")
  @NotNull
  MetaPaginated meta;
  
  public LinksPaginated getLinks() {
    return links;
  }
  public void setLinks(LinksPaginated links) {
    this.links = links;
  }
  public CDRResponsePaginated links(LinksPaginated links) {
    setLinks(links);
    return this;
  }
  
  public MetaPaginated getMeta() {
    return meta;
  }
  
  public void setMeta(MetaPaginated meta) {
    this.meta = meta;
  }
  
  public CDRResponsePaginated meta(MetaPaginated meta) {
    setMeta(meta);
    return this;
  }

}
