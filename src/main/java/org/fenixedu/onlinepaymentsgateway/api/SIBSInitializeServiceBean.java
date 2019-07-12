package org.fenixedu.onlinepaymentsgateway.api;

import org.fenixedu.onlinepaymentsgateway.sibs.sdk.SibsEnvironmentMode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SIBSInitializeServiceBean {

    private String entityId;
    private String bearerToken;

    private String endpointUrl;

    private String paymentEntity;
    private String paymentCurrency;

    private String aesKey;

    private SibsEnvironmentMode environmentMode;

    public SIBSInitializeServiceBean(String entityId, String bearerToken, String endpointUrl, String paymentEntity,
            String paymentCurrency, SibsEnvironmentMode environmentMode) {
        super();
        this.entityId = entityId;
        this.bearerToken = bearerToken;
        this.endpointUrl = endpointUrl;
        this.paymentEntity = paymentEntity;
        this.paymentCurrency = paymentCurrency;
        this.environmentMode = environmentMode;
    }

    public SIBSInitializeServiceBean(String entityId, String aesKey, SibsEnvironmentMode environmentMode) {
        super();
        this.entityId = entityId;
        this.aesKey = aesKey;
        this.environmentMode = environmentMode;
    }

    public SIBSInitializeServiceBean() {
    }

    public boolean isAuthPropertiesValid() {
        boolean returnValue = true;
        returnValue &= entityId != null && !entityId.isEmpty();
        returnValue &= bearerToken != null && !bearerToken.isEmpty();
        returnValue &= environmentMode != null;
        return returnValue;
    }

    public boolean isPaymentPropertiesValid() {
        boolean returnValue = true;
        returnValue &= paymentEntity != null && !paymentEntity.isEmpty();
        returnValue &= paymentCurrency != null && !paymentCurrency.isEmpty();
        return returnValue;
    }

    public boolean isNotificationPropertiesValid() {
        boolean returnValue = true;
        returnValue &= aesKey != null && !aesKey.isEmpty();
        return returnValue;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = "Bearer " + bearerToken;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getPaymentEntity() {
        return paymentEntity;
    }

    public void setPaymentEntity(String paymentEntity) {
        this.paymentEntity = paymentEntity;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public SibsEnvironmentMode getEnvironmentMode() {
        return environmentMode;
    }

    public void setEnvironmentMode(SibsEnvironmentMode environmentMode) {
        this.environmentMode = environmentMode;
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
