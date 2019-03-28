package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public class MbPrepareCheckoutInputBean {

    private BigDecimal amount;
    private String merchantTransactionId;
    private DateTime sibsRefIntDate;
    private DateTime sibsRefLmtDate;

    public MbPrepareCheckoutInputBean(BigDecimal amount, String merchantTransactionId, DateTime sibsRefIntDate,
            DateTime sibsRefLmtDate) {
        super();
        this.amount = amount;
        this.merchantTransactionId = merchantTransactionId;
        this.sibsRefIntDate = sibsRefIntDate;
        this.sibsRefLmtDate = sibsRefLmtDate;
    }

    public MbPrepareCheckoutInputBean() {
    }

    public boolean isPropertiesValid() {
        boolean returnValue = true;
        returnValue &= amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
        returnValue &= merchantTransactionId != null && !merchantTransactionId.isEmpty();
        returnValue &= sibsRefIntDate != null && sibsRefLmtDate != null && sibsRefIntDate.isBefore(sibsRefLmtDate);
        return returnValue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
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
