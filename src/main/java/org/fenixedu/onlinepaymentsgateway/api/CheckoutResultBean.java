package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;

import org.fenixedu.onlinepaymentsgateway.sibs.sdk.SibsResultCodeType;
import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class CheckoutResultBean {

    private String checkoutId;
    private String merchantTransactionId;
    private DateTime timestamp;
    private String shopperResultUrl;
    private BigDecimal paymentAmount;
    private String paymentCurrency;
    private String paymentGatewayResultCode;
    private String paymentGatewayResultDescription;

    private String requestLog;
    private String responseLog;
    private SibsResultCodeType operationResultType;
    private String operationResultDescription;

    private String paymentBrands;

    private Exception exception;

    public CheckoutResultBean(String checkoutId, DateTime timestamp, String shopperResultUrl, BigDecimal paymentAmount,
            String paymentCurrency, SibsResultCodeType operationResultType, String operationResultDescription, String resultCode,
            String resultDescription) {
        super();
        this.checkoutId = checkoutId;
        this.timestamp = timestamp;
        this.shopperResultUrl = shopperResultUrl;
        this.paymentAmount = paymentAmount;
        this.paymentCurrency = paymentCurrency;
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

    public String getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(String checkoutId) {
        this.checkoutId = checkoutId;
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

    public String getShopperResultUrl() {
        return shopperResultUrl;
    }

    public void setShopperResultUrl(String shopperResultUrl) {
        this.shopperResultUrl = shopperResultUrl;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
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

    public String getPaymentBrands() {
        return paymentBrands;
    }

    public void setPaymentBrands(String paymentBrands) {
        this.paymentBrands = paymentBrands;
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