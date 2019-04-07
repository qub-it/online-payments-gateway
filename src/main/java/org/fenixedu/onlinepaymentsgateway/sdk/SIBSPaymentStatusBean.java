package org.fenixedu.onlinepaymentsgateway.sdk;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "paymentType", "paymentBrand", "amount", "currency", "descriptor", "merchantTransactionId", "result",
        "card", "threeDSecure", "customParameters", "risk", "buildNumber", "timestamp", "ndc" })
public class SIBSPaymentStatusBean {

    public SIBSPaymentStatusBean() {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "bin", "last4Digits", "holder", "expiryMonth", "expiryYear" })
    public static class Card {

        @JsonProperty("bin")
        private String bin;
        @JsonProperty("last4Digits")
        private String last4Digits;
        @JsonProperty("holder")
        private String holder;
        @JsonProperty("expiryMonth")
        private String expiryMonth;
        @JsonProperty("expiryYear")
        private String expiryYear;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Card() {
        }

        public String getBin() {
            return bin;
        }

        @JsonProperty("bin")
        public void setBin(String bin) {
            this.bin = bin;
        }

        @JsonProperty("last4Digits")
        public String getLast4Digits() {
            return last4Digits;
        }

        @JsonProperty("last4Digits")
        public void setLast4Digits(String last4Digits) {
            this.last4Digits = last4Digits;
        }

        @JsonProperty("holder")
        public String getHolder() {
            return holder;
        }

        @JsonProperty("holder")
        public void setHolder(String holder) {
            this.holder = holder;
        }

        @JsonProperty("expiryMonth")
        public String getExpiryMonth() {
            return expiryMonth;
        }

        @JsonProperty("expiryMonth")
        public void setExpiryMonth(String expiryMonth) {
            this.expiryMonth = expiryMonth;
        }

        @JsonProperty("expiryYear")
        public String getExpiryYear() {
            return expiryYear;
        }

        @JsonProperty("expiryYear")
        public void setExpiryYear(String expiryYear) {
            this.expiryYear = expiryYear;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "SHOPPER_EndToEndIdentity", "CTPE_DESCRIPTOR_TEMPLATE" })
    public static class CustomParameters {

        @JsonProperty("SHOPPER_EndToEndIdentity")
        private String sHOPPEREndToEndIdentity;
        @JsonProperty("CTPE_DESCRIPTOR_TEMPLATE")
        private String cTPEDESCRIPTORTEMPLATE;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public CustomParameters() {
        }

        @JsonProperty("SHOPPER_EndToEndIdentity")
        public String getSHOPPEREndToEndIdentity() {
            return sHOPPEREndToEndIdentity;
        }

        @JsonProperty("SHOPPER_EndToEndIdentity")
        public void setSHOPPEREndToEndIdentity(String sHOPPEREndToEndIdentity) {
            this.sHOPPEREndToEndIdentity = sHOPPEREndToEndIdentity;
        }

        @JsonProperty("CTPE_DESCRIPTOR_TEMPLATE")
        public String getCTPEDESCRIPTORTEMPLATE() {
            return cTPEDESCRIPTORTEMPLATE;
        }

        @JsonProperty("CTPE_DESCRIPTOR_TEMPLATE")
        public void setCTPEDESCRIPTORTEMPLATE(String cTPEDESCRIPTORTEMPLATE) {
            this.cTPEDESCRIPTORTEMPLATE = cTPEDESCRIPTORTEMPLATE;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "code", "description" })
    public static class Result {

        @JsonProperty("code")
        private String code;
        @JsonProperty("description")
        private String description;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Result() {
        }

        @JsonProperty("code")
        public String getCode() {
            return code;
        }

        @JsonProperty("code")
        public void setCode(String code) {
            this.code = code;
        }

        @JsonProperty("description")
        public String getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(String description) {
            this.description = description;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "score" })
    public static class Risk {

        @JsonProperty("score")
        private String score;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Risk() {
        }

        @JsonProperty("score")
        public String getScore() {
            return score;
        }

        @JsonProperty("score")
        public void setScore(String score) {
            this.score = score;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "eci" })
    public static class ThreeDSecure {

        @JsonProperty("eci")
        private String eci;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public ThreeDSecure() {
        }

        @JsonProperty("eci")
        public String getEci() {
            return eci;
        }

        @JsonProperty("eci")
        public void setEci(String eci) {
            this.eci = eci;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

    @JsonProperty("id")
    private String id;
    @JsonProperty("paymentType")
    private String paymentType;
    @JsonProperty("paymentBrand")
    private String paymentBrand;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("descriptor")
    private String descriptor;
    @JsonProperty("merchantTransactionId")
    private String merchantTransactionId;
    @JsonProperty("result")
    private Result result;
    @JsonProperty("card")
    private Card card;
    @JsonProperty("threeDSecure")
    private ThreeDSecure threeDSecure;
    @JsonProperty("customParameters")
    private CustomParameters customParameters;
    @JsonProperty("risk")
    private Risk risk;
    @JsonProperty("buildNumber")
    private String buildNumber;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("ndc")
    private String ndc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("paymentType")
    public String getPaymentType() {
        return paymentType;
    }

    @JsonProperty("paymentType")
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @JsonProperty("paymentBrand")
    public String getPaymentBrand() {
        return paymentBrand;
    }

    @JsonProperty("paymentBrand")
    public void setPaymentBrand(String paymentBrand) {
        this.paymentBrand = paymentBrand;
    }

    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("descriptor")
    public String getDescriptor() {
        return descriptor;
    }

    @JsonProperty("descriptor")
    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    @JsonProperty("merchantTransactionId")
    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    @JsonProperty("merchantTransactionId")
    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    @JsonProperty("result")
    public Result getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(Result result) {
        this.result = result;
    }

    @JsonProperty("card")
    public Card getCard() {
        return card;
    }

    @JsonProperty("card")
    public void setCard(Card card) {
        this.card = card;
    }

    @JsonProperty("threeDSecure")
    public ThreeDSecure getThreeDSecure() {
        return threeDSecure;
    }

    @JsonProperty("threeDSecure")
    public void setThreeDSecure(ThreeDSecure threeDSecure) {
        this.threeDSecure = threeDSecure;
    }

    @JsonProperty("customParameters")
    public CustomParameters getCustomParameters() {
        return customParameters;
    }

    @JsonProperty("customParameters")
    public void setCustomParameters(CustomParameters customParameters) {
        this.customParameters = customParameters;
    }

    @JsonProperty("risk")
    public Risk getRisk() {
        return risk;
    }

    @JsonProperty("risk")
    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    @JsonProperty("buildNumber")
    public String getBuildNumber() {
        return buildNumber;
    }

    @JsonProperty("buildNumber")
    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("ndc")
    public String getNdc() {
        return ndc;
    }

    @JsonProperty("ndc")
    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
