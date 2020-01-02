package io.biza.cdr.babelfish.response;

import io.biza.cdr.babelfish.enumerations.PayloadTypeCustomer;
import io.biza.cdr.babelfish.model.common.CommonOrganisation;
import io.biza.cdr.babelfish.model.common.CommonPerson;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import javax.validation.constraints.AssertTrue;

public class ResponseCommonCustomerData {

    @BabelFishModelProperty(
        description =  "The type of customer object that is present",
        required = true
    )
    PayloadTypeCustomer customerUType;

    CommonPerson person;
    CommonOrganisation organisation;
    
    @AssertTrue(message = "Only one of Person or Organisation type must be populated and aligned with customerUType")
    private boolean isUTypeAligned() {
        if(PayloadTypeCustomer.PERSON.equals(customerUType)) {
            return person != null && organisation == null;
        }
        
        if(PayloadTypeCustomer.ORGANISATION.equals(customerUType)) {
            return organisation != null && person == null;
        }
        
        return false;
    }
}
