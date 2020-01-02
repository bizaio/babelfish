package io.biza.cdr.babelfish.response;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.banking.BankingAccountDetail;
import io.biza.cdr.babelfish.model.common.Links;
import io.biza.cdr.babelfish.model.common.Meta;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@EqualsAndHashCode(callSuper=true)
@Valid
@BabelFishModel(description =  "Response containing a single Banking Account Detail")
public class ResponseBankingAccountById  extends CDRResponse<Links, Meta> {

	@BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    BankingAccountDetail data;
}
