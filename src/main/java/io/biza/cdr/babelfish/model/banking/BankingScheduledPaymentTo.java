package io.biza.cdr.babelfish.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.enumerations.PayloadTypeBankingScheduledPayment;
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
@BabelFishModel(description =  "Object containing details of the destination of the payment. Used to specify a variety of payment destination types")
public class BankingScheduledPaymentTo {

    @BabelFishModelProperty(
        description =  "The type of object provided that specifies the destination of the funds for the payment.",
        required = true
    )
    @NonNull
    @NotNull
    PayloadTypeBankingScheduledPayment toUType;

    @BabelFishModelProperty(
        description =  "Present if toUType is set to accountId. Indicates that the payment is to another account that is accessible under the current consent"
    )
    String accountId;

    @BabelFishModelProperty(
        description =  "Present if toUType is set to payeeId. Indicates that the payment is to registered payee that can be accessed using the payee end point. If the Bank Payees scope has not been consented to then a payeeId should not be provided and the full payee details should be provided instead"
    )
    String payeeId;

    BankingDomesticPayee domestic;
    BankingBillerPayee biller;
    BankingInternationalPayee international;
    
    @AssertTrue(
            message = "One and only one of accountId, payeeId, domestic, biller, international should be populated based on toUType")
    private boolean isUTypePopulated() {
        if (toUType.equals(PayloadTypeBankingScheduledPayment.ACCOUNT_ID)) {
            return accountId != null && payeeId == null && domestic == null && biller == null && international == null;
        } else if (toUType.equals(PayloadTypeBankingScheduledPayment.PAYEE_ID)) {
            return payeeId != null && accountId == null && domestic == null && biller == null && international == null;
        } else if (toUType.equals(PayloadTypeBankingScheduledPayment.DOMESTIC)) {
            return domestic != null && accountId == null && payeeId == null && biller == null && international == null;
        } else if (toUType.equals(PayloadTypeBankingScheduledPayment.BILLER)) {
            return biller != null && accountId == null && payeeId == null && domestic == null && international == null;
        } else if (toUType.equals(PayloadTypeBankingScheduledPayment.INTERNATIONAL)) {
            return international != null && accountId == null && payeeId == null && domestic == null && biller == null;
        }

        return false;
    }
}
