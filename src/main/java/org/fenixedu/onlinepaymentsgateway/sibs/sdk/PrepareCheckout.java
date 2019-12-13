package org.fenixedu.onlinepaymentsgateway.sibs.sdk;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrepareCheckout {
    private String entityId;
    private String amount;
    private String currency;
    private PaymentType paymentType;
    private String merchantTransactionId;
    private String SIBSMULTIBANCO_PtmntEntty;
    private String SIBSMULTIBANCO_RefIntlDtTm;
    private String SIBSMULTIBANCO_RefLmtDtTm;

    private SibsEnvironmentMode environmentMode;

    private String billingCity;
    private String billingCountry;
    private String billingStreet1;
    private String billingPostcode;
    private String customerEmail;
    private String cardHolder;

    public PrepareCheckout(String entityId, String amount, String currency, String paymentType, String sIBSMULTIBANCO_PtmntEntty,
            String sIBSMULTIBANCO_RefIntlDtTm, String sIBSMULTIBANCO_RefLmtDtTm, SibsEnvironmentMode environmentMode) {
        super();
        this.entityId = entityId;
        this.amount = amount;
        this.currency = currency;
        this.paymentType = PaymentType.valueOf(paymentType);
        this.SIBSMULTIBANCO_PtmntEntty = sIBSMULTIBANCO_PtmntEntty;
        this.SIBSMULTIBANCO_RefIntlDtTm = sIBSMULTIBANCO_RefIntlDtTm;
        this.SIBSMULTIBANCO_RefLmtDtTm = sIBSMULTIBANCO_RefLmtDtTm;
        this.environmentMode = environmentMode;
    }

    public PrepareCheckout(String entityId, String amount, String currency, String paymentType,
            SibsEnvironmentMode environmentMode) {
        super();
        this.entityId = entityId;
        this.amount = amount;
        this.currency = currency;
        this.paymentType = PaymentType.valueOf(paymentType);
        this.environmentMode = environmentMode;
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
    
    public PrepareCheckout() {
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = PaymentType.valueOf(paymentType);
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    public String getSIBSMULTIBANCO_PtmntEntty() {
        return SIBSMULTIBANCO_PtmntEntty;
    }

    public void setSIBSMULTIBANCO_PtmntEntty(String sIBSMULTIBANCO_PtmntEntty) {
        this.SIBSMULTIBANCO_PtmntEntty = sIBSMULTIBANCO_PtmntEntty;
    }

    public String getSIBSMULTIBANCO_RefIntlDtTm() {
        return SIBSMULTIBANCO_RefIntlDtTm;
    }

    public void setSIBSMULTIBANCO_RefIntlDtTm(String sIBSMULTIBANCO_RefIntlDtTm) {
        this.SIBSMULTIBANCO_RefIntlDtTm = sIBSMULTIBANCO_RefIntlDtTm;
    }

    public String getSIBSMULTIBANCO_RefLmtDtTm() {
        return SIBSMULTIBANCO_RefLmtDtTm;
    }

    public void setSIBSMULTIBANCO_RefLmtDtTm(String sIBSMULTIBANCO_RefLmtDtTm) {
        this.SIBSMULTIBANCO_RefLmtDtTm = sIBSMULTIBANCO_RefLmtDtTm;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public SibsEnvironmentMode getEnvironmentMode() {
        return environmentMode;
    }

    public void setEnvironmentMode(SibsEnvironmentMode environmentMode) {
        this.environmentMode = environmentMode;
    }

    public MultivaluedMap<String, String> asMap() {
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.add("entityId", entityId);
        form.add("amount", amount);
        form.add("currency", currency);
        form.add("paymentType", paymentType.toString());
        if (environmentMode == SibsEnvironmentMode.TEST_MODE_EXTERNAL) {
            form.add("testMode", "EXTERNAL");
            form.add("customParameters[SIBS_ENV]", "QLY");
        } else if (environmentMode == SibsEnvironmentMode.TEST_MODE_INTERNAL) {
            form.add("testMode", "INTERNAL");
            form.add("customParameters[SIBS_ENV]", "QLY");
        } else if (environmentMode == SibsEnvironmentMode.PRODUCTION) {

        } else {
            throw new RuntimeException("Unknow environment mode.");
        }

        form.add("customParameters[SIBSMULTIBANCO_PtmntEntty]", SIBSMULTIBANCO_PtmntEntty);
        form.add("customParameters[SIBSMULTIBANCO_RefIntlDtTm]", SIBSMULTIBANCO_RefIntlDtTm);
        form.add("customParameters[SIBSMULTIBANCO_RefLmtDtTm]", SIBSMULTIBANCO_RefLmtDtTm);
        
        if(this.cardHolder != null) {
            form.add("card.holder", this.cardHolder);
        }
        
        if(this.customerEmail != null) {
            form.add("customer.email", this.customerEmail);
        }
        
        if(this.billingCountry != null) {
            form.add("billing.country", this.billingCountry);
        }
        
        if(this.billingCity != null) {
            form.add("billing.city", this.billingCity);
        }
        
        if(this.billingStreet1 != null) {
            form.add("billing.street1", this.billingStreet1);
        }
        
        if(this.billingPostcode != null) {
            form.add("billing.postcode", this.billingPostcode);
        }
        
        if (this.merchantTransactionId != null) {
            form.add("merchantTransactionId", merchantTransactionId);
        }
        
        return form;
    }

    @Override
    public String toString() {
        MultivaluedMap<String, String> map = this.asMap();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
