package io.biza.cdr.babelfish.model.banking;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.enumerations.BankingPaymentNonBusinessDayTreatment;
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
@BabelFishModel(description =  "Indicates that the schedule of payments is defined by a series of intervals. Mandatory if recurrenceUType is set to intervalSchedule")
public class BankingScheduledPaymentRecurrenceIntervalSchedule {

    @BabelFishModelProperty(
        description =  "The limit date after which no more payments should be made using this schedule. If both finalPaymentDate and paymentsRemaining are present then payments will stop according to the most constraining value. If neither field is present the payments will continue indefinitely",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = LocalDateToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalDateConverter.class)
    private LocalDate finalPaymentDate;

    @BabelFishModelProperty(
        description =  "Indicates the number of payments remaining in the schedule. If both finalPaymentDate and paymentsRemaining are present then payments will stop according to the most constraining value, If neither field is present the payments will continue indefinitely"
    )
    @Min(1)
    Integer paymentsRemaining;

    @BabelFishModelProperty(
        description =  "Enumerated field giving the treatment where a scheduled payment date is not a business day.  If absent assumed to be ON"
    )
    @Builder.Default
    BankingPaymentNonBusinessDayTreatment nonBusinessDayTreatment = BankingPaymentNonBusinessDayTreatment.ON;

    @BabelFishModelProperty(
        description =  "An array of interval objects defining the payment schedule.  Each entry in the array is additive, in that it adds payments to the overall payment schedule.  If multiple intervals result in a payment on the same day then only one payment will be made. Must have at least one entry",
        required = true
    )
    @NonNull
    @NotNull
    List<BankingScheduledPaymentInterval> intervals;
}
