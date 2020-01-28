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

import java.util.List;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductBundleV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductConstraintV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductDepositRateV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductEligibilityV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeatureV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeeV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductLendingRateV1;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public abstract class BankingProductDetail extends BankingProduct {
   List<BankingProductBundleV1> bundles;
   List<BankingProductFeatureV1> features;
   List<BankingProductConstraintV1> constraints;
   List<BankingProductEligibilityV1> eligibility;
   List<BankingProductFeeV1> fees;
   List<BankingProductDepositRateV1> depositRates;
   List<BankingProductLendingRateV1> lendingRates;
}
