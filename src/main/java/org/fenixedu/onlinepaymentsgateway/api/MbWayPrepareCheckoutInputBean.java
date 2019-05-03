package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MbWayPrepareCheckoutInputBean {
    public BigDecimal amount;
    public String merchantTransactionId;
    public String phoneNumber;

    public MbWayPrepareCheckoutInputBean(BigDecimal amount, String merchantTransactionId, String phoneNumber) {
        super();
        this.amount = amount;
        this.merchantTransactionId = merchantTransactionId;
        this.phoneNumber = phoneNumber;
    }

    public MbWayPrepareCheckoutInputBean(BigDecimal amount, String phoneNumber) {
        super();
        this.amount = amount;
        this.phoneNumber = phoneNumber;
    }

    public MbWayPrepareCheckoutInputBean() {
    }

    public boolean isAmountValid() {
        boolean returnValue = true;
        returnValue &= this.amount != null && getAmount().compareTo(BigDecimal.ZERO) > 0;
        return returnValue;
    }

    public boolean isNumberValid() {
        boolean returnValue = true;
        returnValue &= phoneNumber != null && phoneNumber.matches("^351[#]\\d{9}$");
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
