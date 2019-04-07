package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;

public class PrepareCheckoutInputBean {
    public BigDecimal amount;
    public String merchantTransactionId;
    public String shopperResultUrl;
    public Boolean useCreditCard = false;
    public Boolean useMBway = false;
    public Boolean useMB = false;

    public PrepareCheckoutInputBean(BigDecimal amount, String merchantTransactionId, String shopperResultUrl) {
        super();
        this.amount = amount;
        this.merchantTransactionId = merchantTransactionId;
        this.shopperResultUrl = shopperResultUrl;
    }

    public PrepareCheckoutInputBean() {
    }

    public boolean isPropertiesValid() {
        boolean returnValue = true;
        returnValue &= amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
        returnValue &= merchantTransactionId != null && !merchantTransactionId.isEmpty();
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

}