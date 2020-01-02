package io.biza.cdr.babelfish.model.banking;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.enumerations.PayloadTypeBankingScheduledPaymentRecurrence;
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
@BabelFishModel(description =  "Describes the detail of the scheduled payment")
public class BankingScheduledPaymentRecurrence {

    @BabelFishModelProperty(
        description =  "The date of the next payment under the recurrence schedule",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = LocalDateToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalDateConverter.class)
    private LocalDate nextPaymentDate;

    @BabelFishModelProperty(
        description =  "The type of recurrence used to define the schedule",
        required = true
    )
    @NonNull
    @NotNull
    private PayloadTypeBankingScheduledPaymentRecurrence recurrenceUType;

    private BankingScheduledPaymentRecurrenceOnceOff onceOff;
    private BankingScheduledPaymentRecurrenceIntervalSchedule intervalSchedule;
    private BankingScheduledPaymentRecurrenceLastWeekday lastWeekDay;
    private BankingScheduledPaymentRecurrenceEventBased eventBased;
    
    @AssertTrue(message = "One and Only One Recurrence Type Object must be populated to align with recurrencyUType")
    private boolean isRecurrenceTypeCorrect() {
        if(recurrenceUType.equals(PayloadTypeBankingScheduledPaymentRecurrence.ONCE_OFF)) {
            return onceOff != null && intervalSchedule == null && lastWeekDay == null && eventBased == null ? true : false;
        } else  if(recurrenceUType.equals(PayloadTypeBankingScheduledPaymentRecurrence.INTERVAL_SCHEDULE)) {
            return intervalSchedule != null && onceOff == null && lastWeekDay == null  && eventBased == null ? true : false;
        } else  if(recurrenceUType.equals(PayloadTypeBankingScheduledPaymentRecurrence.LAST_WEEKDAY)) {
            return lastWeekDay != null && intervalSchedule == null && intervalSchedule == null && eventBased == null ? true : false;
        } else  if(recurrenceUType.equals(PayloadTypeBankingScheduledPaymentRecurrence.EVENT_BASED)) {
            return eventBased != null && onceOff == null && intervalSchedule == null  && lastWeekDay == null ? true : false;
        }
        return false;
    }
}
