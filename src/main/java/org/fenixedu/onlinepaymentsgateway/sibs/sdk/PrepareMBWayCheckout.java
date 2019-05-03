package org.fenixedu.onlinepaymentsgateway.sibs.sdk;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.fenixedu.onlinepaymentsgateway.api.CustomerDataInputBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PrepareMBWayCheckout {
    private String entityId;
    private String amount;
    private String currency;
    private PaymentType paymentType;
    private String merchantTransactionId;
    private PaymentBrand paymentBrand;
    private CustomerDataInputBean customerInput;
    private String phoneNumber;

    public PrepareMBWayCheckout(String entityId, String amount, String currency, String paymentType, String paymentBrand,
            String phoneNumber, CustomerDataInputBean customerInput) {
        super();
        this.entityId = entityId;
        this.amount = amount;
        this.currency = currency;
        this.paymentType = PaymentType.valueOf(paymentType);
        this.paymentBrand = PaymentBrand.valueOf(paymentBrand);
        this.phoneNumber = phoneNumber;
        this.customerInput = customerInput;
    }

    public PrepareMBWayCheckout() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MultivaluedMap<String, String> asMap() {
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.add("entityId", entityId);
        form.add("amount", amount);
        form.add("currency", currency);
        form.add("paymentType", paymentType.toString());
        form.add("paymentBrand", paymentBrand.toString());
        form.add("virtualAccount.accountId", phoneNumber);
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
