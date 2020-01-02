package io.biza.cdr.babelfish.model.common;

import java.time.LocalDateTime;
import java.time.Period;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.PeriodToStringConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.converters.StringToPeriodConverter;
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
@BabelFishModel(description =  "Outage Detail")
public class CommonDiscoveryOutage {

    @BabelFishModelProperty(
        description =  "Date and time that the outage is scheduled to begin",
        required = true
    )
    @NonNull
    @NotNull
    @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
    @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
    private LocalDateTime outageTime;

    @BabelFishModelProperty(
        description =  "Planned duration of the outage. Formatted according to [ISO 8601 Durations](https://en.wikipedia.org/wiki/ISO_8601#Durations)",
        required = true
    )
    @NonNull
    @NotNull
    @JsonSerialize(converter = PeriodToStringConverter.class)
    @JsonDeserialize(converter = StringToPeriodConverter.class)
    Period duration;

    @BabelFishModelProperty(
        description =  "Flag that indicates, if present and set to true, that the outage is only partial meaning that only a subset of normally available end points will be affected by the outage"
    )
    Boolean isPartial;

    @BabelFishModelProperty(
        description =  "Provides an explanation of the current outage that can be displayed to an end customer",
        required = true
    )
    @NonNull
    @NotNull
    String explanation;
}
