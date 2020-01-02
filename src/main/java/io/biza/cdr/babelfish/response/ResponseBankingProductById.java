package io.biza.cdr.babelfish.response;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.banking.BankingProductDetail;
import io.biza.cdr.babelfish.model.common.Links;
import io.biza.cdr.babelfish.model.common.Meta;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@Valid
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@JsonIgnoreProperties({"xV"})
public class ResponseBankingProductById  extends CDRResponse<Links, Meta> {

    @BabelFishModelProperty(
        required = true
    )
    BankingProductDetail data;
}
