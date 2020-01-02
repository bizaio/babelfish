package io.biza.cdr.babelfish.model.common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
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
@BabelFishModel(description =  "Person definition in brief")
public class CommonPerson {

    @BabelFishModelProperty(
        description =  "The date and time that this record was last updated by the customer.  If no update has occurred then this date should reflect the initial creation date for the data"
    )
    @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
    @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
    private LocalDateTime lastUpdateTime;

    @BabelFishModelProperty(
        description =  "For people with single names this field need not be present.  The single name should be in the lastName field"
    )
    String firstName;

    @BabelFishModelProperty(
        description =  "For people with single names the single name should be in this field",
        required = true
    )
    @NonNull
    @NotNull
    String lastName;

    @BabelFishModelProperty(
        description =  "Field is mandatory but array may be empty",
        required = true
    )
    @NonNull
    @NotNull
    @Builder.Default
    List<String> middleNames = new ArrayList<String>();

    @BabelFishModelProperty(
        description =  "Also known as title or salutation.  The prefix to the name (e.g. Mr, Mrs, Ms, Miss, Sir, etc)"
    )
    String prefix;

    @BabelFishModelProperty(
        description =  "Used for a trailing suffix to the name (e.g. Jr)"
    )
    String suffix;

    @BabelFishModelProperty(
        description =  "Value is a valid [ANZCO v1.2](http://www.abs.gov.au/ANZSCO) Standard Occupation classification."
    )
    String occupationCode;
}
