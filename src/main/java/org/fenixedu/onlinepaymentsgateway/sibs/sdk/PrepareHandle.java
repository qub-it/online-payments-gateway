package org.fenixedu.onlinepaymentsgateway.sibs.sdk;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrepareHandle {
    private String entityId;
    private String amount;
    private String currency;
    private PaymentType paymentType;
    private SibsEnvironmentMode environmentMode;

    public PrepareHandle(String entityId, String amount, String currency, String paymentType,
            SibsEnvironmentMode environmentMode) {
        super();
        this.entityId = entityId;
        this.amount = amount;
        this.currency = currency;
        this.paymentType = PaymentType.valueOf(paymentType);
        this.environmentMode = environmentMode;
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

    public SibsEnvironmentMode getEnvironmentMode() {
        return environmentMode;
    }

    public void setEnvironmentMode(SibsEnvironmentMode environmentMode) {
        this.environmentMode = environmentMode;
    }

    public MultivaluedMap<String, String> asMap() {
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.add("entityId", entityId);
        form.add("amount", amount.toString());
        form.add("currency", currency);
        form.add("paymentType", paymentType.toString());
        if (environmentMode == SibsEnvironmentMode.TEST_MODE_EXTERNAL) {
            form.add("testMode", "EXTERNAL");
            form.add("customParameters[SIBS_ENV]", "QLY");
        } else if (environmentMode == SibsEnvironmentMode.TEST_MODE_INTERNAL) {
            form.add("testMode", "INTERNAL");
            form.add("customParameters[SIBS_ENV]", "QLY");
        } else if (environmentMode == SibsEnvironmentMode.PRODUCTION) {

        } else {
            throw new RuntimeException("Unknow environment mode.");
        }
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
