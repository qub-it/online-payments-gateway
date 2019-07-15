package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;

import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PaymentBrand;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PaymentType;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.SibsResultCodeType;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MbCheckoutResultBean {

    private String transactionId;
    private String merchantTransactionId;
    private DateTime timestamp;

    private BigDecimal amount;
    private String currency;
    private PaymentBrand paymentBrand;
    private PaymentType paymentType;
    private String paymentEntity;
    private String paymentReference;
    private String paymentRefInitialDate;
    private String paymentRefLimitDate;

    private String paymentGatewayResultCode;
    private String paymentGatewayResultDescription;

    private String requestLog;
    private String responseLog;
    private SibsResultCodeType operationResultType;
    private String operationResultDescription;
    private String paymentState;

    private Exception exception;

    public MbCheckoutResultBean(String transactionId, String merchantTransactionId, DateTime timestamp, String amount,
            String currency, String paymentBrand, String paymentType, String paymentEntity, String paymentReference,
            String paymentRefInitialDate, String paymentRefLimitDate, SibsResultCodeType operationResultType,
            String operationResultDescription, String resultCode, String resultDescription) {
        super();
        this.transactionId = transactionId;
        this.merchantTransactionId = merchantTransactionId;
        this.timestamp = timestamp;
        this.amount = new BigDecimal(amount);
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

    public MbCheckoutResultBean() {
        super();
    }

    public boolean isOperationSuccess() {
        return this.operationResultType.isSuccess();
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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

    public String getPaymentRefInitialDate() {
        return paymentRefInitialDate;
    }

    public void setPaymentRefInitialDate(String paymentRefInitialDate) {
        this.paymentRefInitialDate = paymentRefInitialDate;
    }

    public String getPaymentRefLimitDate() {
        return paymentRefLimitDate;
    }

    public void setPaymentRefLimitDate(String paymentRefLimitDate) {
        this.paymentRefLimitDate = paymentRefLimitDate;
    }

    public String getPaymentGatewayResultCode() {
        return paymentGatewayResultCode;
    }

    public void setPaymentGatewayResultCode(String paymentGatewayResultCode) {
        this.paymentGatewayResultCode = paymentGatewayResultCode;
    }

    public SibsResultCodeType getOperationResultType() {
        return operationResultType;
    }

    public void setOperationResultType(SibsResultCodeType operationResultType) {
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

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}