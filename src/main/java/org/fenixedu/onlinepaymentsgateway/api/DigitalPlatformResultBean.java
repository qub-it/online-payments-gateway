package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public interface DigitalPlatformResultBean {

    public String getTransactionId();

    public String getMerchantTransactionId();

    public String getPaymentType();

    public String getPaymentBrand();

    public String getTimestamp();

    public DateTime getPaymentDate();

    public boolean isPaid();

    public BigDecimal getAmount();

    public String getPaymentResultCode();

    public String getPaymentResultDescription();

}
