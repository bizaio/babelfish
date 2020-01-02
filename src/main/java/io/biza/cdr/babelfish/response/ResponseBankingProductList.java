package io.biza.cdr.babelfish.response;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.common.LinksPaginated;
import io.biza.cdr.babelfish.model.common.MetaPaginated;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@EqualsAndHashCode(callSuper=true)
@Valid
@BabelFishModel(description =  "Response containing a Product List")
public class ResponseBankingProductList  extends CDRResponse<LinksPaginated, MetaPaginated> {

    @BabelFishModelProperty(
        required = true
    )
    ResponseBankingProductListData data;
}
