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
package io.biza.babelfish.abstracts.cdr.banking.product;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.converter.cdr.BigDecimalToRateStringConverter;
import io.biza.babelfish.converter.cdr.RateStringToBigDecimalConverter;
import io.biza.babelfish.converter.cdr.UriStringToUriConverter;
import io.biza.babelfish.converter.cdr.UriToUriStringConverter;
import io.biza.babelfish.enumerations.cdr.BankingProductDiscountType;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeeDiscountEligibilityV1;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public abstract class BankingProductDiscount {
   String description;
   BankingProductDiscountType type;
   BigDecimal amount;
   BigDecimal balanceRate;
   BigDecimal transactionRate;
   BigDecimal accruedRate;
   BigDecimal feeRate;
   String additionalValue;
   String additionalInfo;
   URI additionalInfoUri;
   List<BankingProductFeeDiscountEligibilityV1> eligibility;
}
