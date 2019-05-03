package org.fenixedu.onlinepaymentsgateway.sibs.sdk;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.fenixedu.onlinepaymentsgateway.api.CustomerDataInputBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrepareMBCheckout {
    private String entityId;
    private String amount;
    private String currency;
    private PaymentType paymentType;
    private String merchantTransactionId;
    private PaymentBrand paymentBrand;
    private CustomerDataInputBean customerInput;
    private String SIBSMULTIBANCO_PtmntEntty;
    private String SIBSMULTIBANCO_RefIntlDtTm;
    private String SIBSMULTIBANCO_RefLmtDtTm;
    private String billingCountry;

    public PrepareMBCheckout(String entityId, String amount, String currency, String paymentType, String paymentBrand,
            String sIBSMULTIBANCO_PtmntEntty, String sIBSMULTIBANCO_RefIntlDtTm, String sIBSMULTIBANCO_RefLmtDtTm,
            String billingCountry, CustomerDataInputBean customerInput) {
        super();
        this.entityId = entityId;
        this.amount = amount;
        this.currency = currency;
        this.paymentType = PaymentType.valueOf(paymentType);
        this.paymentBrand = PaymentBrand.valueOf(paymentBrand);
        this.SIBSMULTIBANCO_PtmntEntty = sIBSMULTIBANCO_PtmntEntty;
        this.SIBSMULTIBANCO_RefIntlDtTm = sIBSMULTIBANCO_RefIntlDtTm;
        this.SIBSMULTIBANCO_RefLmtDtTm = sIBSMULTIBANCO_RefLmtDtTm;
        this.billingCountry = billingCountry;
        this.customerInput = customerInput;
    }

    public PrepareMBCheckout() {
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

    public PaymentBrand getPaymentBrand() {
        return paymentBrand;
    }

    public void setPaymentBrand(String paymentBrand) {
        this.paymentBrand = PaymentBrand.valueOf(paymentBrand);
    }

    public CustomerDataInputBean getCustomerInput() {
        return customerInput;
    }

    public void setCustomerInputBean(CustomerDataInputBean customerInput) {
        this.customerInput = customerInput;
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

    public MultivaluedMap<String, String> asMap() {
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.add("entityId", entityId);
        form.add("amount", amount);
        form.add("currency", currency);
        form.add("paymentType", paymentType.toString());
        form.add("paymentBrand", paymentBrand.toString());
        form.add("customParameters[SIBSMULTIBANCO_PtmntEntty]", SIBSMULTIBANCO_PtmntEntty);
        form.add("customParameters[SIBSMULTIBANCO_RefIntlDtTm]", SIBSMULTIBANCO_RefIntlDtTm);
        form.add("customParameters[SIBSMULTIBANCO_RefLmtDtTm]", SIBSMULTIBANCO_RefLmtDtTm);
        form.add("billing.country", billingCountry);
        //form.add("testMode", "EXTERNAL");
        //form.add("customParameters[SIBS_ENV]", "QLY");
        if (merchantTransactionId != null) {
            form.add("merchantTransactionId", merchantTransactionId);
        }
        if (customerInput != null) {
            if (customerInput.getIp() != null) {
                form.add("customer.ip", customerInput.getIp());
            }
            if (customerInput.getSurname() != null) {
                form.add("customer.surname", customerInput.getSurname());
            }
            if (customerInput.getGivenName() != null) {
                form.add("customer.givenName", customerInput.getGivenName());
            }
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
