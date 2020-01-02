package io.biza.cdr.babelfish.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.enumerations.PayloadTypeBankingDomesticPayee;
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
@BabelFishModel(description = "Representation of a Domestic Payee Detail")
public class BankingDomesticPayee {

    @BabelFishModelProperty(description = "Type of account object included.", required = true)
    @NotNull
    @NonNull
    PayloadTypeBankingDomesticPayee payeeAccountUType;

    private BankingDomesticPayeeAccount account;
    private BankingDomesticPayeeCard card;
    private BankingDomesticPayeePayId payId;

    @AssertTrue(message = "Payee Account Type must supply matching Payee Account Type Specific Information")
    private boolean isAccountTypeCorrect() {
        if (payeeAccountUType.equals(PayloadTypeBankingDomesticPayee.ACCOUNT)) {
            return account != null && card == null && payId == null ? true : false;
        } else if (payeeAccountUType.equals(PayloadTypeBankingDomesticPayee.CARD)) {
            return card != null && account == null && payId == null ? true : false;
        } else if (payeeAccountUType.equals(PayloadTypeBankingDomesticPayee.PAY_ID)) {
            return payId != null && account == null && card == null ? true : false;
        }
        return false;
    }
}
