package org.fenixedu.onlinepaymentsgateway.api;

import org.fenixedu.onlinepaymentsgateway.sdk.PaymentBrand;
import org.fenixedu.onlinepaymentsgateway.sdk.PaymentType;
import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MbCheckoutResultBean {

    private String id;
    private String merchantTransactionId;
    private String timestamp; //Date

    private String amount;
    private String currency;
    private PaymentBrand paymentBrand;
    private PaymentType paymentType;
    private String paymentEntity;
    private String paymentReference;
    private DateTime paymentRefInitialDate;
    private DateTime paymentRefLimitDate;

    private String paymentGatewayResultCode;
    private String paymentGatewayResultDescription;

    private String requestLog;
    private String responseLog;
    private OnlinePaymentOperationResultType operationResultType;
    private String operationResultDescription;

    public MbCheckoutResultBean(String id, String merchantTransactionId, String timestamp, String amount, String currency,
            String paymentBrand, String paymentType, String paymentEntity, String paymentReference,
            DateTime paymentRefInitialDate, DateTime paymentRefLimitDate, OnlinePaymentOperationResultType operationResultType,
            String operationResultDescription, String resultCode, String resultDescription) {
        super();
        this.id = id;
        this.merchantTransactionId = merchantTransactionId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.currency = currency;
        this.paymentBrand = PaymentBrand.valueOf(paymentBrand);
        this.paymentType = PaymentType.valueOf(paymentType);
        this.paymentEntity = paymentEntity;
        this.paymentReference = paymentReference;
        this.paymentRefInitialDate = paymentRefInitialDate;
        this.paymentRefLimitDate = paymentRefLimitDate;
        this.operationResultType = operationResultType;
        this.operationResultDescription = operationResultDescription;
        this.paymentGatewayResultCode = resultCode;
        this.paymentGatewayResultDescription = resultDescription;
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

    public PaymentBrand getPaymentBrand() {
        return paymentBrand;
    }

    public void setPaymentBrand(String paymentBrand) {
        this.paymentBrand = PaymentBrand.valueOf(paymentBrand);
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = PaymentType.valueOf(paymentType);
    }

    public String getPaymentEntity() {
        return paymentEntity;
    }

    public void setPaymentEntity(String paymentEntity) {
        this.paymentEntity = paymentEntity;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getPaymentGatewayResultDescription() {
        return paymentGatewayResultDescription;
    }

    public void setPaymentGatewayResultDescription(String paymentGatewayResultDescription) {
        this.paymentGatewayResultDescription = paymentGatewayResultDescription;
    }

    public DateTime getPaymentRefInitialDate() {
        return paymentRefInitialDate;
    }

    public void setPaymentRefInitialDate(DateTime paymentRefInitialDate) {
        this.paymentRefInitialDate = paymentRefInitialDate;
    }

    public DateTime getPaymentRefLimitDate() {
        return paymentRefLimitDate;
    }

    public void setPaymentRefLimitDate(DateTime paymentRefLimitDate) {
        this.paymentRefLimitDate = paymentRefLimitDate;
    }

    public String getPaymentGatewayResultCode() {
        return paymentGatewayResultCode;
    }

    public void setPaymentGatewayResultCode(String paymentGatewayResultCode) {
        this.paymentGatewayResultCode = paymentGatewayResultCode;
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