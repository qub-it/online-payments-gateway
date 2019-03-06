package org.fenixedu.onlinepaymentsgateway.api;

public class SIBSInitializeServiceBean {

    private String userId;
    private String password;
    private String entityId;

    private String endpointUrl;

    private String mbEntity;
    private String currency;

    private String aesKey;

    public SIBSInitializeServiceBean(String userId, String password, String entityId, String endpointUrl, String mbEntity,
            String currency) {
        super();
        this.userId = userId;
        this.password = password;
        this.entityId = entityId;
        this.endpointUrl = endpointUrl;
        this.mbEntity = mbEntity;
        this.currency = currency;
    }

    public SIBSInitializeServiceBean() {
    }

    public boolean isPropertyValid() {
        boolean returnValue = true;
        returnValue &= userId != null && !userId.isEmpty();
        returnValue &= password != null && !userId.isEmpty();
        //TODO completar beans

        return returnValue;
    }

    public String getMbEntity() {
        return mbEntity;
    }

    public void setMbEntity(String mbEntity) {
        this.mbEntity = mbEntity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setaesKey(String aesKey) {
        this.aesKey = aesKey;

    }

}
