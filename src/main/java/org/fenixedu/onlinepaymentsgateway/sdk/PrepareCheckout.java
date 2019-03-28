package org.fenixedu.onlinepaymentsgateway.sdk;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrepareCheckout {
    private String entityId;
    private String amount;
    private String currency;
    private PaymentType paymentType;

    public PrepareCheckout(String entityId, String amount, String currency, String paymentType) {
        super();
        this.entityId = entityId;
        this.amount = amount;
        this.currency = currency;
        this.paymentType = PaymentType.valueOf(paymentType);
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = PaymentType.valueOf(paymentType);
    }

    public MultivaluedMap<String, String> asMap() {
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.add(entityId, entityId);
        form.add("amount", amount);
        form.add("currency", currency);
        form.add("paymentType", paymentType.toString());
        return form;
    }

    @Override
    public String toString() {
        MultivaluedMap<String, String> map = this.asMap();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
