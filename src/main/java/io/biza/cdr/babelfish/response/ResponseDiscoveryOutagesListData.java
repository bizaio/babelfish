package io.biza.cdr.babelfish.response;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.model.common.CommonDiscoveryOutage;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.NonNull;

public class ResponseDiscoveryOutagesListData {

    @BabelFishModelProperty(
        description =  "List of scheduled outages. Property is mandatory but may contain and empty list if no outages are scheduled",
        required = true
    )
    @NotNull
    @NonNull
    List<CommonDiscoveryOutage> outages;
}
