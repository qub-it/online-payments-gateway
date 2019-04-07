package org.fenixedu.onlinepaymentsgateway.api;

import org.fenixedu.onlinepaymentsgateway.sdk.PaymentBrand;
import org.fenixedu.onlinepaymentsgateway.sdk.PaymentType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentStatusBean {

    private String id;
    private String merchantTransactionId;
    private String timestamp; //Date

    private PaymentType paymentType;
    private PaymentBrand paymentBrand;
    private String paymentAmount;
    private String paymentCurrency;

    private String paymentGatewayResultCode;
    private String paymentGatewayResultDescription;

    private String requestLog;
    private String responseLog;
    private OnlinePaymentOperationResultType operationResultType;
    private String operationResultDescription;

    public PaymentStatusBean(String id, String merchantTransactionId, String timestamp, PaymentType paymentType,
            PaymentBrand paymentBrand, String paymentAmount, String paymentCurrency, String paymentGatewayResultCode,
            String paymentGatewayResultDescription, OnlinePaymentOperationResultType operationResultType,
            String operationResultDescription) {
        super();
        this.id = id;
        this.merchantTransactionId = merchantTransactionId;
        this.timestamp = timestamp;
        this.paymentType = paymentType;
        this.paymentBrand = paymentBrand;
        this.paymentAmount = paymentAmount;
        this.paymentCurrency = paymentCurrency;
        this.paymentGatewayResultCode = paymentGatewayResultCode;
        this.paymentGatewayResultDescription = paymentGatewayResultDescription;
        this.operationResultType = operationResultType;
        this.operationResultDescription = operationResultDescription;
    }

    public PaymentStatusBean() {
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentBrand getPaymentBrand() {
        return paymentBrand;
    }

    public void setPaymentBrand(PaymentBrand paymentBrand) {
        this.paymentBrand = paymentBrand;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
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
