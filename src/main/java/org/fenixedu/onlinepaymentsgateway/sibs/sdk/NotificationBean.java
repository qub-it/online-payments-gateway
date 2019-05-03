package org.fenixedu.onlinepaymentsgateway.sibs.sdk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "payload" })
public class NotificationBean {

    public NotificationBean() {
        super();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "amount", "authentication", "billing", "currency", "customParameters", "customer", "descriptor", "id",
            "merchantAccountId", "merchantTransactionId", "ndc", "paymentBrand", "paymentType", "presentationAmount",
            "presentationCurrency", "redirect", "referencedId", "result", "resultDetails", "risk", "timestamp" })
    public static class Payload {

        public Payload() {
            super();
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({ "entityId" })
        public static class Authentication {

            @JsonProperty("entityId")
            private String entityId;
            @JsonIgnore
            private Map<String, Object> additionalProperties = new HashMap<String, Object>();

            public Authentication() {
                super();
            }

            @JsonProperty("entityId")
            public String getEntityId() {
                return entityId;
            }

            @JsonProperty("entityId")
            public void setEntityId(String entityId) {
                this.entityId = entityId;
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
        @JsonPropertyOrder({ "country" })
        public static class Billing {

            @JsonProperty("country")
            private String country;
            @JsonIgnore
            private Map<String, Object> additionalProperties = new HashMap<String, Object>();

            public Billing() {
                super();
            }

            @JsonProperty("country")
            public String getCountry() {
                return country;
            }

            @JsonProperty("country")
            public void setCountry(String country) {
                this.country = country;
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
        @JsonPropertyOrder({ "SIBSMULTIBANCO_PtmntEntty", "SIBSMULTIBANCO_RefIntlDtTm", "SIBSMULTIBANCO_RefLmtDtTm" })
        public static class CustomParameters {

            @JsonProperty("SIBSMULTIBANCO_PtmntEntty")
            private String sIBSMULTIBANCOPtmntEntty;
            @JsonProperty("SIBSMULTIBANCO_RefIntlDtTm")
            private String sIBSMULTIBANCORefIntlDtTm;
            @JsonProperty("SIBSMULTIBANCO_RefLmtDtTm")
            private String sIBSMULTIBANCORefLmtDtTm;
            @JsonIgnore
            private Map<String, Object> additionalProperties = new HashMap<String, Object>();

            public CustomParameters() {
                super();
            }

            public String getSIBSMULTIBANCOPtmntEntty() {
                return sIBSMULTIBANCOPtmntEntty;
            }

            @JsonProperty("SIBSMULTIBANCO_PtmntEntty")
            public void setSIBSMULTIBANCOPtmntEntty(String sIBSMULTIBANCOPtmntEntty) {
                this.sIBSMULTIBANCOPtmntEntty = sIBSMULTIBANCOPtmntEntty;
            }

            @JsonProperty("SIBSMULTIBANCO_RefIntlDtTm")
            public String getSIBSMULTIBANCORefIntlDtTm() {
                return sIBSMULTIBANCORefIntlDtTm;
            }

            @JsonProperty("SIBSMULTIBANCO_RefIntlDtTm")
            public void setSIBSMULTIBANCORefIntlDtTm(String sIBSMULTIBANCORefIntlDtTm) {
                this.sIBSMULTIBANCORefIntlDtTm = sIBSMULTIBANCORefIntlDtTm;
            }

            @JsonProperty("SIBSMULTIBANCO_RefLmtDtTm")
            public String getSIBSMULTIBANCORefLmtDtTm() {
                return sIBSMULTIBANCORefLmtDtTm;
            }

            @JsonProperty("SIBSMULTIBANCO_RefLmtDtTm")
            public void setSIBSMULTIBANCORefLmtDtTm(String sIBSMULTIBANCORefLmtDtTm) {
                this.sIBSMULTIBANCORefLmtDtTm = sIBSMULTIBANCORefLmtDtTm;
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
        @JsonPropertyOrder({ "givenName", "ip", "surname" })
        public static class Customer {

            @JsonProperty("givenName")
            private String givenName;
            @JsonProperty("ip")
            private String ip;
            @JsonProperty("surname")
            private String surname;
            @JsonIgnore
            private Map<String, Object> additionalProperties = new HashMap<String, Object>();

            public Customer() {
                super();
            }

            public String getGivenName() {
                return givenName;
            }

            @JsonProperty("givenName")
            public void setGivenName(String givenName) {
                this.givenName = givenName;
            }

            @JsonProperty("ip")
            public String getIp() {
                return ip;
            }

            @JsonProperty("ip")
            public void setIp(String ip) {
                this.ip = ip;
            }

            @JsonProperty("surname")
            public String getSurname() {
                return surname;
            }

            @JsonProperty("surname")
            public void setSurname(String surname) {
                this.surname = surname;
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
        @JsonPropertyOrder({ "parameters" })
        public static class Redirect {

            @JsonProperty("parameters")
            private List<Object> parameters = null;
            @JsonIgnore
            private Map<String, Object> additionalProperties = new HashMap<String, Object>();

            public Redirect() {
                super();
            }

            public List<Object> getParameters() {
                return parameters;
            }

            @JsonProperty("parameters")
            public void setParameters(List<Object> parameters) {
                this.parameters = parameters;
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
                super();
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
        @JsonPropertyOrder({ "ConnectorTxID1", "ConnectorTxID2", "ConnectorTxID3" })
        public static class ResultDetails {

            @JsonProperty("ConnectorTxID1")
            private String connectorTxID1;
            @JsonProperty("ConnectorTxID2")
            private String connectorTxID2;
            @JsonProperty("ConnectorTxID3")
            private String connectorTxID3;
            @JsonIgnore
            private Map<String, Object> additionalProperties = new HashMap<String, Object>();

            public ResultDetails() {
                super();
            }

            @JsonProperty("ConnectorTxID1")
            public String getConnectorTxID1() {
                return connectorTxID1;
            }

            @JsonProperty("ConnectorTxID1")
            public void setConnectorTxID1(String connectorTxID1) {
                this.connectorTxID1 = connectorTxID1;
            }

            @JsonProperty("ConnectorTxID2")
            public String getConnectorTxID2() {
                return connectorTxID2;
            }

            @JsonProperty("ConnectorTxID2")
            public void setConnectorTxID2(String connectorTxID2) {
                this.connectorTxID2 = connectorTxID2;
            }

            @JsonProperty("ConnectorTxID3")
            public String getConnectorTxID3() {
                return connectorTxID3;
            }

            @JsonProperty("ConnectorTxID3")
            public void setConnectorTxID3(String connectorTxID3) {
                this.connectorTxID3 = connectorTxID3;
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
                super();
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

        @JsonProperty("amount")
        private String amount;
        @JsonProperty("authentication")
        private Authentication authentication;
        @JsonProperty("billing")
        private Billing billing;
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("customParameters")
        private CustomParameters customParameters;
        @JsonProperty("customer")
        private Customer customer;
        @JsonProperty("descriptor")
        private String descriptor;
        @JsonProperty("id")
        private String id;
        @JsonProperty("merchantAccountId")
        private String merchantAccountId;
        @JsonProperty("merchantTransactionId")
        private String merchantTransactionId;
        @JsonProperty("ndc")
        private String ndc;
        @JsonProperty("paymentBrand")
        private String paymentBrand;
        @JsonProperty("paymentType")
        private String paymentType;
        @JsonProperty("presentationAmount")
        private String presentationAmount;
        @JsonProperty("presentationCurrency")
        private String presentationCurrency;
        @JsonProperty("redirect")
        private Redirect redirect;
        @JsonProperty("referencedId")
        private String referencedId;
        @JsonProperty("result")
        private Result result;
        @JsonProperty("resultDetails")
        private ResultDetails resultDetails;
        @JsonProperty("risk")
        private Risk risk;
        @JsonProperty("timestamp")
        private String timestamp;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("amount")
        public String getAmount() {
            return amount;
        }

        @JsonProperty("amount")
        public void setAmount(String amount) {
            this.amount = amount;
        }

        @JsonProperty("authentication")
        public Authentication getAuthentication() {
            return authentication;
        }

        @JsonProperty("authentication")
        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }

        @JsonProperty("billing")
        public Billing getBilling() {
            return billing;
        }

        @JsonProperty("billing")
        public void setBilling(Billing billing) {
            this.billing = billing;
        }

        @JsonProperty("currency")
        public String getCurrency() {
            return currency;
        }

        @JsonProperty("currency")
        public void setCurrency(String currency) {
            this.currency = currency;
        }

        @JsonProperty("customParameters")
        public CustomParameters getCustomParameters() {
            return customParameters;
        }

        @JsonProperty("customParameters")
        public void setCustomParameters(CustomParameters customParameters) {
            this.customParameters = customParameters;
        }

        @JsonProperty("customer")
        public Customer getCustomer() {
            return customer;
        }

        @JsonProperty("customer")
        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        @JsonProperty("descriptor")
        public String getDescriptor() {
            return descriptor;
        }

        @JsonProperty("descriptor")
        public void setDescriptor(String descriptor) {
            this.descriptor = descriptor;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("merchantAccountId")
        public String getMerchantAccountId() {
            return merchantAccountId;
        }

        @JsonProperty("merchantAccountId")
        public void setMerchantAccountId(String merchantAccountId) {
            this.merchantAccountId = merchantAccountId;
        }

        @JsonProperty("merchantTransactionId")
        public String getMerchantTransactionId() {
            return merchantTransactionId;
        }

        @JsonProperty("merchantTransactionId")
        public void setMerchantTransactionId(String merchantTransactionId) {
            this.merchantTransactionId = merchantTransactionId;
        }

        @JsonProperty("ndc")
        public String getNdc() {
            return ndc;
        }

        @JsonProperty("ndc")
        public void setNdc(String ndc) {
            this.ndc = ndc;
        }

        @JsonProperty("paymentBrand")
        public String getPaymentBrand() {
            return paymentBrand;
        }

        @JsonProperty("paymentBrand")
        public void setPaymentBrand(String paymentBrand) {
            this.paymentBrand = paymentBrand;
        }

        @JsonProperty("paymentType")
        public String getPaymentType() {
            return paymentType;
        }

        @JsonProperty("paymentType")
        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        @JsonProperty("presentationAmount")
        public String getPresentationAmount() {
            return presentationAmount;
        }

        @JsonProperty("presentationAmount")
        public void setPresentationAmount(String presentationAmount) {
            this.presentationAmount = presentationAmount;
        }

        @JsonProperty("presentationCurrency")
        public String getPresentationCurrency() {
            return presentationCurrency;
        }

        @JsonProperty("presentationCurrency")
        public void setPresentationCurrency(String presentationCurrency) {
            this.presentationCurrency = presentationCurrency;
        }

        @JsonProperty("redirect")
        public Redirect getRedirect() {
            return redirect;
        }

        @JsonProperty("redirect")
        public void setRedirect(Redirect redirect) {
            this.redirect = redirect;
        }

        @JsonProperty("referencedId")
        public String getReferencedId() {
            return referencedId;
        }

        @JsonProperty("referencedId")
        public void setReferencedId(String referencedId) {
            this.referencedId = referencedId;
        }

        @JsonProperty("result")
        public Result getResult() {
            return result;
        }

        @JsonProperty("result")
        public void setResult(Result result) {
            this.result = result;
        }

        @JsonProperty("resultDetails")
        public ResultDetails getResultDetails() {
            return resultDetails;
        }

        @JsonProperty("resultDetails")
        public void setResultDetails(ResultDetails resultDetails) {
            this.resultDetails = resultDetails;
        }

        @JsonProperty("risk")
        public Risk getRisk() {
            return risk;
        }

        @JsonProperty("risk")
        public void setRisk(Risk risk) {
            this.risk = risk;
        }

        @JsonProperty("timestamp")
        public String getTimestamp() {
            return timestamp;
        }

        @JsonProperty("timestamp")
        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
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

    @JsonProperty("type")
    private String type;
    @JsonProperty("payload")
    private Payload payload;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("payload")
    public Payload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
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