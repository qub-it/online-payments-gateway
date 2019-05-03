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

    public PrepareCheckoutInputBean(BigDecimal amount, String merchantTransactionId, String shopperResultUrl,
            DateTime sibsRefIntDate, DateTime sibsRefLmtDate) {
        super();
        this.amount = amount;
        this.merchantTransactionId = merchantTransactionId;
        this.shopperResultUrl = shopperResultUrl;
        this.sibsRefIntDate = sibsRefIntDate;
        this.sibsRefLmtDate = sibsRefLmtDate;
    }

    public PrepareCheckoutInputBean(BigDecimal amount, String shopperResultUrl, DateTime sibsRefIntDate,
            DateTime sibsRefLmtDate) {
        super();
        this.amount = amount;
        this.shopperResultUrl = shopperResultUrl;
        this.sibsRefIntDate = sibsRefIntDate;
        this.sibsRefLmtDate = sibsRefLmtDate;
    }

    public PrepareCheckoutInputBean() {
    }

    public boolean isPropertiesValid() {
        boolean returnValue = true;
        returnValue &= amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
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