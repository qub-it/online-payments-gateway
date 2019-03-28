package org.fenixedu.onlinepaymentsgateway.sdk;

import javax.ws.rs.core.MultivaluedMap;

import org.fenixedu.onlinepaymentsgateway.api.CustomerInputBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrepareMBCheckout {

    private PrepareCheckout prepareCheckout;
    private PaymentBrand paymentBrand;
    private CustomerInputBean customerInput;
    private String merchantTransactionId;
    private String SIBSMULTIBANCO_PtmntEntty;
    private String SIBSMULTIBANCO_RefIntlDtTm;
    private String SIBSMULTIBANCO_RefLmtDtTm;
    private String billingCountry;

    public PrepareMBCheckout(PrepareCheckout prepareCheckout, String paymentBrand, String merchantTransactionId,
            String sIBSMULTIBANCO_PtmntEntty, String sIBSMULTIBANCO_RefIntlDtTm, String sIBSMULTIBANCO_RefLmtDtTm,
            String billingCountry, CustomerInputBean customerInput) {
        super();
        this.prepareCheckout = prepareCheckout;
        this.paymentBrand = PaymentBrand.valueOf(paymentBrand);
        this.merchantTransactionId = merchantTransactionId;
        this.SIBSMULTIBANCO_PtmntEntty = sIBSMULTIBANCO_PtmntEntty;
        this.SIBSMULTIBANCO_RefIntlDtTm = sIBSMULTIBANCO_RefIntlDtTm;
        this.SIBSMULTIBANCO_RefLmtDtTm = sIBSMULTIBANCO_RefLmtDtTm;
        this.billingCountry = billingCountry;
        this.customerInput = customerInput;
    }

    public PrepareCheckout getPrepareCheckout() {
        return prepareCheckout;
    }

    public void setPrepareCheckout(PrepareCheckout prepareCheckout) {
        this.prepareCheckout = prepareCheckout;
    }

    public PaymentBrand getPaymentBrand() {
        return paymentBrand;
    }

    public void setPaymentBrand(String paymentBrand) {
        this.paymentBrand = PaymentBrand.valueOf(paymentBrand);
    }

    public CustomerInputBean getCustomerInput() {
        return customerInput;
    }

    public void setCustomerInputBean(CustomerInputBean customerInput) {
        this.customerInput = customerInput;
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

    public MultivaluedMap<String, String> asMap() {
        MultivaluedMap<String, String> form = prepareCheckout.asMap();
        form.add("paymentBrand", paymentBrand.toString());
        form.add("merchantTransactionId", merchantTransactionId);
        form.add("customParameters[SIBSMULTIBANCO_PtmntEntty]", SIBSMULTIBANCO_PtmntEntty);
        form.add("customParameters[SIBSMULTIBANCO_RefIntlDtTm]", SIBSMULTIBANCO_RefIntlDtTm);
        form.add("customParameters[SIBSMULTIBANCO_RefLmtDtTm]", SIBSMULTIBANCO_RefLmtDtTm);
        form.add("billing[country]", billingCountry);
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
            if (customerInput.getCountry() != null) {
                form.add("billing.country", customerInput.getCountry());
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
