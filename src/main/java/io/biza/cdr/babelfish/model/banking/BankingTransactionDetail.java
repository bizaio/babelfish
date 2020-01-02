package io.biza.cdr.babelfish.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description =  "Banking Transaction Detailed Information", parent = BankingTransaction.class)
public class BankingTransactionDetail {
	
	@JsonUnwrapped
	@BabelFishModelProperty(
			hidden = true
	)
	@Valid
	BankingTransaction bankingTransaction;

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    BankingTransactionDetailExtendedData extendedData;
}
