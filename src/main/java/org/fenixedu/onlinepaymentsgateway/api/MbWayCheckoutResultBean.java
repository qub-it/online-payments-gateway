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
public class MbWayCheckoutResultBean {

    private String transactionId;
    private String merchantTransactionId;
    private DateTime timestamp;

    private BigDecimal amount;
    private String currency;
    private PaymentBrand paymentBrand;
    private PaymentType paymentType;
    private String phoneNumber;
    private String acquirerResponse;

    private String paymentGatewayResultCode;
    private String paymentGatewayResultDescription;

    private String requestLog;
    private String responseLog;
    private SibsResultCodeType operationResultType;
    private String operationResultDescription;
    private String paymentState;

    private Exception exception;

    public MbWayCheckoutResultBean(String transactionId, DateTime timestamp, BigDecimal amount, String currency,
            String paymentBrand, String paymentType, String phoneNumber, String acquirerResponse,
            SibsResultCodeType operationResultType, String operationResultDescription, String resultCode,
            String resultDescription) {
        super();
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.currency = currency;
        this.paymentBrand = PaymentBrand.valueOf(paymentBrand);
        this.paymentType = PaymentType.valueOf(paymentType);
        this.phoneNumber = phoneNumber;
        this.acquirerResponse = acquirerResponse;
        this.operationResultType = operationResultType;
        this.operationResultDescription = operationResultDescription;
        this.paymentGatewayResultCode = resultCode;
        this.paymentGatewayResultDescription = resultDescription;
    }

    public MbWayCheckoutResultBean() {
        super();
    }

    public boolean isOperationSuccess() {
        return this.operationResultType.isSuccess();
        //  "000.100.110".equals(this.sibsResultCode); //detalhe de comunicaçao deve tar no sdk.
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAcquirerResponse() {
        return acquirerResponse;
    }

    public void setAcquirerResponse(String acquirerResponse) {
        this.acquirerResponse = acquirerResponse;
    }

    public String getPaymentGatewayResultDescription() {
        return paymentGatewayResultDescription;
    }

    public void setPaymentGatewayResultDescription(String paymentGatewayResultDescription) {
        this.paymentGatewayResultDescription = paymentGatewayResultDescription;
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
