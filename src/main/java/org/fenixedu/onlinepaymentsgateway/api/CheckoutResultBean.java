package org.fenixedu.onlinepaymentsgateway.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CheckoutResultBean {

    private String id;
    private String merchantTransactionId;
    private String timestamp; //Date
    private String shopperResultUrl;

    private String paymentGatewayResultCode;
    private String paymentGatewayResultDescription;

    private String requestLog;
    private String responseLog;
    private OnlinePaymentOperationResultType operationResultType;
    private String operationResultDescription;

    private String paymentBrands;

    public CheckoutResultBean(String id, String merchantTransactionId, String timestamp, String shopperResultUrl,
            OnlinePaymentOperationResultType operationResultType, String operationResultDescription, String resultCode,
            String resultDescription) {
        super();
        this.id = id;
        this.merchantTransactionId = merchantTransactionId;
        this.timestamp = timestamp;
        this.shopperResultUrl = shopperResultUrl;
        this.paymentGatewayResultCode = resultCode;
        this.paymentGatewayResultDescription = resultDescription;
        this.operationResultType = operationResultType;
        this.operationResultDescription = operationResultDescription;
    }

    public CheckoutResultBean() {
    }

    public boolean isOperationSuccess() {
        return this.operationResultType.isSuccess();
        //  "000.100.110".equals(this.sibsResultCode); //detalhe de comunicaçao deve tar no sdk.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getShopperResultUrl() {
        return shopperResultUrl;
    }

    public void setShopperResultUrl(String shopperResultUrl) {
        this.shopperResultUrl = shopperResultUrl;
    }

    public String getPaymentGatewayResultCode() {
        return paymentGatewayResultCode;
    }

    public void setPaymentGatewayResultCode(String paymentGatewayResultCode) {
        this.paymentGatewayResultCode = paymentGatewayResultCode;
    }

    public String getPaymentGatewayResultDescription() {
        return paymentGatewayResultDescription;
    }

    public void setPaymentGatewayResultDescription(String paymentGatewayResultDescription) {
        this.paymentGatewayResultDescription = paymentGatewayResultDescription;
    }

    public String getRequestLog() {
        return requestLog;
    }

    public void setRequestLog(String requestLog) {
        this.requestLog = requestLog;
    }

    public String getResponseLog() {
        return responseLog;
    }

    public void setResponseLog(String responseLog) {
        this.responseLog = responseLog;
    }

    public OnlinePaymentOperationResultType getOperationResultType() {
        return operationResultType;
    }

    public void setOperationResultType(OnlinePaymentOperationResultType operationResultType) {
        this.operationResultType = operationResultType;
    }

    public String getOperationResultDescription() {
        return operationResultDescription;
    }

    public void setOperationResultDescription(String operationResultDescription) {
        this.operationResultDescription = operationResultDescription;
    }

    public String getPaymentBrands() {
        return paymentBrands;
    }

    public void setPaymentBrands(String paymentBrands) {
        this.paymentBrands = paymentBrands;
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