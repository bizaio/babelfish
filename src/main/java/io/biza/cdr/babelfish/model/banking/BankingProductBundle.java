package io.biza.cdr.babelfish.model.banking;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
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
@BabelFishModel(description =  "Banking Product Bundle Definition")
public class BankingProductBundle {

    @BabelFishModelProperty(
        description =  "Name of the bundle",
        required = true
    )
    @NonNull
    @NotNull
    String name;

    @BabelFishModelProperty(
        description =  "Description of the bundle",
        required = true
    )
    @NonNull
    @NotNull
    String description;

    @BabelFishModelProperty(
        description =  "Display text providing more information on the bundle"
    )
    String additionalInfo;

    @BabelFishModelProperty(
        description =  "Link to a web page with more information on the bundle criteria and benefits",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI additionalInfoUri;

    @BabelFishModelProperty(
        description =  "Array of product IDs for products included in the bundle that are available via the product end points.  Note that this array is not intended to represent a comprehensive model of the products included in the bundle and some products available for the bundle may not be available via the product reference end points"
    )
    List<String> productIds;
}
