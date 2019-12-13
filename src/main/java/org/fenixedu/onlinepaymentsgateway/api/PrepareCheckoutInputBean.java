package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrepareCheckoutInputBean {
    public BigDecimal amount;
    public String merchantTransactionId;
    public String shopperResultUrl;
    private DateTime sibsRefIntDate;
    private DateTime sibsRefLmtDate;
    public Boolean useCreditCard = false;
    public Boolean useMBway = false;
    public Boolean useMB = false;

    private String billingCountry;
    private String billingCity;
    private String billingStreet1;
    private String billingPostcode;
    private String customerEmail;
    private String cardHolder;
    
    public PrepareCheckoutInputBean(BigDecimal amount, String merchantTransactionId, String shopperResultUrl,
            DateTime sibsRefIntDate, DateTime sibsRefLmtDate) {
        super();
        this.amount = amount;
        this.merchantTransactionId = merchantTransactionId;
        this.shopperResultUrl = shopperResultUrl;
        this.sibsRefIntDate = sibsRefIntDate;
        this.sibsRefLmtDate = sibsRefLmtDate;
    }

    public PrepareCheckoutInputBean(BigDecimal amount, String merchantTransactionId, String shopperResultUrl) {
        super();
        this.amount = amount;
        this.merchantTransactionId = merchantTransactionId;
        this.shopperResultUrl = shopperResultUrl;
    }

    public PrepareCheckoutInputBean() {
    }
    
    public void fillBillingData(final String cardHolder, final String billingCountry, final String billingCity, 
            final String billingStreet1, final String billingPostcode, final String customerEmail) {
        this.cardHolder = cardHolder;
        this.billingCountry = billingCountry;
        this.billingCity = billingCity;
        this.billingStreet1 = billingStreet1;
        this.billingPostcode = billingPostcode;
        this.customerEmail = customerEmail;
    }
    
    public boolean isPropertiesValid() {
        boolean returnValue = true;
        returnValue &= amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
        returnValue &= shopperResultUrl != null && !shopperResultUrl.isEmpty();
        return returnValue;
    }

    public boolean isMBPropertiesValid() {
        boolean returnValue = true;
        returnValue &= this.amount != null && getAmount().compareTo(BigDecimal.ZERO) > 0;
        returnValue &= sibsRefIntDate != null && sibsRefLmtDate != null && sibsRefIntDate.isBefore(sibsRefLmtDate);
        returnValue &= shopperResultUrl != null && !shopperResultUrl.isEmpty();
        return returnValue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    public String getShopperResultUrl() {
        return shopperResultUrl;
    }

    public void setShopperResultUrl(String shopperResultUrl) {
        this.shopperResultUrl = shopperResultUrl;
    }

    public String getSibsRefIntDate() {
        return sibsRefIntDate.toString();
    }

    public void setSibsRefIntDate(DateTime sibsRefIntDate) {
        this.sibsRefIntDate = sibsRefIntDate;
    }

    public String getSibsRefLmtDate() {
        return sibsRefLmtDate.toString();
    }

    public void setSibsRefLmtDate(DateTime sibsRefLmtDate) {
        this.sibsRefLmtDate = sibsRefLmtDate;
    }

    public Boolean getUseCreditCard() {
        return useCreditCard;
    }

    public void setUseCreditCard(Boolean useCreditCard) {
        this.useCreditCard = useCreditCard;
    }

    public Boolean getUseMBway() {
        return useMBway;
    }

    public void setUseMBway(Boolean useMBway) {
        this.useMBway = useMBway;
    }

    public Boolean getUseMB() {
        return useMB;
    }

    public void setUseMB(Boolean useMB) {
        this.useMB = useMB;
    }
    
    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingStreet1() {
        return billingStreet1;
    }

    public void setBillingStreet1(String billingStreet1) {
        this.billingStreet1 = billingStreet1;
    }

    public String getBillingPostcode() {
        return billingPostcode;
    }

    public void setBillingPostcode(String billingPostcode) {
        this.billingPostcode = billingPostcode;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
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