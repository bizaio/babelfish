package io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.scheduled;

import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingScheduledPaymentRecurrence;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceEventBasedV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceIntervalScheduleV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceLastWeekdayV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceOnceOffV1;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingScheduledPaymentRecurrenceV1 {

  public abstract PayloadTypeBankingScheduledPaymentRecurrence type();

  public abstract BankingScheduledPaymentRecurrenceOnceOffV1 onceOff();

  public abstract BankingScheduledPaymentRecurrenceIntervalScheduleV1 intervalSchedule();

  public abstract BankingScheduledPaymentRecurrenceLastWeekdayV1 lastWeekDay();

  public abstract BankingScheduledPaymentRecurrenceEventBasedV1 eventBased();


  @AssertTrueBabelfish(message = "Only Once Off must be present when uType is set to ONCE_OFF",
      fields = {"type", "onceOff"})
  private boolean isOnceOffPopulated() {
    return FormatChecker.isDefined(type())
        && type().equals(PayloadTypeBankingScheduledPaymentRecurrence.ONCE_OFF)
            ? onceOff() != null && intervalSchedule() == null && lastWeekDay() == null
                && eventBased() == null
            : true;
  }

  @AssertTrueBabelfish(
      message = "Only Once Off must be present when uType is set to INTERVAL_SCHEDULE",
      fields = {"type", "intervalSchedule"})
  private boolean isIntervalPopulated() {
    return FormatChecker.isDefined(type())
        && type().equals(PayloadTypeBankingScheduledPaymentRecurrence.INTERVAL_SCHEDULE)
            ? intervalSchedule() != null && onceOff() == null && lastWeekDay() == null
                && eventBased() == null
            : true;
  }

  @AssertTrueBabelfish(message = "Only Once Off must be present when uType is set to LAST_WEEKDAY",
      fields = {"type", "lastWeekDay"})
  private boolean isLastWeekDayPopulated() {
    return FormatChecker.isDefined(type())
        && type().equals(PayloadTypeBankingScheduledPaymentRecurrence.LAST_WEEKDAY)
            ? lastWeekDay() != null && intervalSchedule() == null && onceOff() == null
                && eventBased() == null
            : true;
  }

  @AssertTrueBabelfish(message = "Only Once Off must be present when uType is set to EVENT_BASED",
      fields = {"type", "eventBased"})
  private boolean isEventBasedPopulated() {
    return FormatChecker.isDefined(type())
        && type().equals(PayloadTypeBankingScheduledPaymentRecurrence.EVENT_BASED)
            ? eventBased() != null && intervalSchedule() == null && lastWeekDay() == null
                && onceOff() == null
            : true;
  }
}
