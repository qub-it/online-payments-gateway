package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;

import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PaymentType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HandleRequestBean {
    public BigDecimal amount;
    public PaymentType paymentType;

    public HandleRequestBean(BigDecimal amount, String paymentType) {
        super();
        this.amount = amount;
        this.paymentType = PaymentType.valueOf(paymentType);
    }

    public HandleRequestBean() {
    }

    public boolean isPropertiesValid() {
        boolean returnValue = true;
        returnValue &= this.amount != null && getAmount().compareTo(BigDecimal.ZERO) > 0;
        return returnValue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = PaymentType.valueOf(paymentType);
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
