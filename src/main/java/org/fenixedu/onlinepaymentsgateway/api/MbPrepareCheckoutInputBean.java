package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public class MbPrepareCheckoutInputBean extends PrepareCheckoutInputBean {

    private DateTime sibsRefIntDate;
    private DateTime sibsRefLmtDate;

    public MbPrepareCheckoutInputBean(BigDecimal amount, String merchantTransactionId, DateTime sibsRefIntDate,
            DateTime sibsRefLmtDate) {
        super();
        this.sibsRefIntDate = sibsRefIntDate;
        this.sibsRefLmtDate = sibsRefLmtDate;
    }

    public MbPrepareCheckoutInputBean() {
    }

    public boolean isPropertiesValid() {
        boolean returnValue = true;
        returnValue &= this.amount != null && getAmount().compareTo(BigDecimal.ZERO) > 0;
        returnValue &= merchantTransactionId != null && !merchantTransactionId.isEmpty();
        returnValue &= sibsRefIntDate != null && sibsRefLmtDate != null && sibsRefIntDate.isBefore(sibsRefLmtDate);
        return returnValue;
    }

    public DateTime getSibsRefIntDate() {
        return sibsRefIntDate;
    }

    public void setSibsRefIntDate(DateTime sibsRefIntDate) {
        this.sibsRefIntDate = sibsRefIntDate;
    }

    public DateTime getSibsRefLmtDate() {
        return sibsRefLmtDate;
    }

    public void setSibsRefLmtDate(DateTime sibsRefLmtDate) {
        this.sibsRefLmtDate = sibsRefLmtDate;
    }
}
