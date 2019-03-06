package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public class MbPrepareCheckoutInputBean {
    
    private BigDecimal amount;
    private String merchantTransactionId; 
    private DateTime sIBSMULTIBANCO_RefIntlDtTm;
    private DateTime sIBSMULTIBANCO_RefLmtDtTm;  
    
    public boolean isPropertiesValid() {
        boolean returnValue = true;
        returnValue &= amount!= null && amount.compareTo(BigDecimal.ZERO)> 0;
        //TODO completar beans
        
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
    public DateTime getsIBSMULTIBANCO_RefIntlDtTm() {
        return sIBSMULTIBANCO_RefIntlDtTm;
    }
    public void setsIBSMULTIBANCO_RefIntlDtTm(DateTime sIBSMULTIBANCO_RefIntlDtTm) {
        this.sIBSMULTIBANCO_RefIntlDtTm = sIBSMULTIBANCO_RefIntlDtTm;
    }
    public DateTime getsIBSMULTIBANCO_RefLmtDtTm() {
        return sIBSMULTIBANCO_RefLmtDtTm;
    }
    public void setsIBSMULTIBANCO_RefLmtDtTm(DateTime sIBSMULTIBANCO_RefLmtDtTm) {
        this.sIBSMULTIBANCO_RefLmtDtTm = sIBSMULTIBANCO_RefLmtDtTm;
    }
}
