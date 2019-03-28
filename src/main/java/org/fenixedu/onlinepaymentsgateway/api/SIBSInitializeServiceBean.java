package org.fenixedu.onlinepaymentsgateway.api;

public class SIBSInitializeServiceBean {

    private String entityId;
    private String bearerToken;

    private String endpointUrl;

    private String paymentEntity;
    private String paymentCurrency;

    private String aesKey;

    public SIBSInitializeServiceBean(String entityId, String bearerToken, String endpointUrl, String paymentEntity,
            String paymentCurrency) {
        super();
        this.entityId = entityId;
        this.bearerToken = bearerToken;
        this.endpointUrl = endpointUrl;
        this.paymentEntity = paymentEntity;
        this.paymentCurrency = paymentCurrency;
    }

    public SIBSInitializeServiceBean(String entityId, String bearerToken, String aesKey) {
        super();
        this.entityId = entityId;
        this.bearerToken = bearerToken;
        this.aesKey = aesKey;
    }

    public SIBSInitializeServiceBean() {
    }

    public boolean isAuthPropertiesValid() {
        boolean returnValue = true;
        returnValue &= entityId != null && !entityId.isEmpty();
        returnValue &= bearerToken != null && !bearerToken.isEmpty();
        return returnValue;
    }

    public boolean isPaymentPropertiesValid() {
        boolean returnValue = true;
        returnValue &= endpointUrl != null && !endpointUrl.isEmpty();
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

}
