package io.biza.cdr.babelfish.model.banking;

import java.net.URI;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description =  "Defines a condition for the applicability of a tiered rate")
public class BankingProductRateCondition {

    @BabelFishModelProperty(
        description =  "Display text providing more information on the condition"
    )
    String additionalInfo;

    @BabelFishModelProperty(
        description =  "Link to a web page with more information on this condition",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI additionalInfoUri;
}
