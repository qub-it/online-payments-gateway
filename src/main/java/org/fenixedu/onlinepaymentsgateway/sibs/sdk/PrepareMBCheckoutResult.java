package org.fenixedu.onlinepaymentsgateway.sibs.sdk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "paymentType", "amount", "currency", "descriptor", "merchantTransactionId", "result", "resultDetails",
        "customer", "billing", "customParameters", "buildNumber", "timestamp", "ndc" })
public class PrepareMBCheckoutResult {

    public PrepareMBCheckoutResult() {
        super();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "code", "description", "parameterErrors" })
    public static class Result {

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({ "name", "value", "message" })
        public static class ParameterError {

            @JsonProperty("name")
            private String name;
            @JsonProperty("value")
            private String value;
            @JsonProperty("message")
            private String message;
            @JsonIgnore
            private Map<String, Object> additionalProperties = new HashMap<String, Object>();

            public ParameterError() {
                super();
            }

            @JsonProperty("name")
            public String getName() {
                return name;
            }

            @JsonProperty("name")
            public void setName(String name) {
                this.name = name;
            }

            @JsonProperty("value")
            public String getValue() {
                return value;
            }

            @JsonProperty("value")
            public void setValue(String value) {
                this.value = value;
            }

            @JsonProperty("message")
            public String getMessage() {
                return message;
            }

            @JsonProperty("message")
            public void setMessage(String message) {
                this.message = message;
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

        @JsonProperty("code")
        private String code;
        @JsonProperty("description")
        private String description;
        @JsonProperty("parameterErrors")
        private List<ParameterError> parameterErrors;
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

        @JsonProperty("parameterErrors")
        public List<ParameterError> getParameterErrors() {
            return parameterErrors;
        }

        @JsonProperty("parameterErrors")
        public void setParameterErrors(List<ParameterError> parameterErrors) {
            this.parameterErrors = parameterErrors;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "refLmtDtTm", "amount", "ptmntEntty", "ConnectorTxID1", "ConnectorTxID3", "RcptTxId", "ConnectorTxID2",
            "pmtRef", "uuid", "pmtRefNtty", "ExtendedDescription", "AcquirerResponse", "TxDtTm" })
    public static class ResultDetails {

        @JsonProperty("refLmtDtTm")
        private String refLmtDtTm;
        @JsonProperty("amount")
        private String amount;
        @JsonProperty("ptmntEntty")
        private String ptmntEntty;
        @JsonProperty("ConnectorTxID1")
        private String connectorTxID1;
        @JsonProperty("ConnectorTxID3")
        private String connectorTxID3;
        @JsonProperty("RcptTxId")
        private String rcptTxId;
        @JsonProperty("ConnectorTxID2")
        private String connectorTxID2;
        @JsonProperty("pmtRef")
        private String pmtRef;
        @JsonProperty("uuid")
        private String uuid;
        @JsonProperty("pmtRefNtty")
        private String pmtRefNtty;
        @JsonProperty("ExtendedDescription")
        private String extendedDescription;
        @JsonProperty("AcquirerResponse")
        private String acquirerResponse;
        @JsonProperty("TxDtTm")
        private String txDtTm;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public ResultDetails() {
        }

        @JsonProperty("refLmtDtTm")
        public String getRefLmtDtTm() {
            return refLmtDtTm;
        }

        @JsonProperty("refLmtDtTm")
        public void setRefLmtDtTm(String refLmtDtTm) {
            this.refLmtDtTm = refLmtDtTm;
        }

        @JsonProperty("amount")
        public String getAmount() {
            return amount;
        }

        @JsonProperty("amount")
        public void setAmount(String amount) {
            this.amount = amount;
        }

        @JsonProperty("ptmntEntty")
        public String getPtmntEntty() {
            return ptmntEntty;
        }

        @JsonProperty("ptmntEntty")
        public void setPtmntEntty(String ptmntEntty) {
            this.ptmntEntty = ptmntEntty;
        }

        @JsonProperty("ConnectorTxID1")
        public String getConnectorTxID1() {
            return connectorTxID1;
        }

        @JsonProperty("ConnectorTxID1")
        public void setConnectorTxID1(String connectorTxID1) {
            this.connectorTxID1 = connectorTxID1;
        }

        @JsonProperty("ConnectorTxID3")
        public String getConnectorTxID3() {
            return connectorTxID3;
        }

        @JsonProperty("ConnectorTxID3")
        public void setConnectorTxID3(String connectorTxID3) {
            this.connectorTxID3 = connectorTxID3;
        }

        @JsonProperty("RcptTxId")
        public String getRcptTxId() {
            return rcptTxId;
        }

        @JsonProperty("RcptTxId")
        public void setRcptTxId(String rcptTxId) {
            this.rcptTxId = rcptTxId;
        }

        @JsonProperty("ConnectorTxID2")
        public String getConnectorTxID2() {
            return connectorTxID2;
        }

        @JsonProperty("ConnectorTxID2")
        public void setConnectorTxID2(String connectorTxID2) {
            this.connectorTxID2 = connectorTxID2;
        }

        @JsonProperty("pmtRef")
        public String getPmtRef() {
            return pmtRef;
        }

        @JsonProperty("pmtRef")
        public void setPmtRef(String pmtRef) {
            this.pmtRef = pmtRef;
        }

        @JsonProperty("uuid")
        public String getUuid() {
            return uuid;
        }

        @JsonProperty("uuid")
        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        @JsonProperty("pmtRefNtty")
        public String getPmtRefNtty() {
            return pmtRefNtty;
        }

        @JsonProperty("pmtRefNtty")
        public void setPmtRefNtty(String pmtRefNtty) {
            this.pmtRefNtty = pmtRefNtty;
        }

        @JsonProperty("ExtendedDescription")
        public String getExtendedDescription() {
            return extendedDescription;
        }

        @JsonSetter("ExtendedDescription")
        public void setExtendedDescription(String extendedDescription) {
            this.extendedDescription = extendedDescription;
        }

        @JsonProperty("AcquirerResponse")
        public String getAcquirerResponse() {
            return acquirerResponse;
        }

        @JsonProperty("AcquirerResponse")
        public void setAcquirerResponse(String acquirerResponse) {
            this.acquirerResponse = acquirerResponse;
        }

        @JsonProperty("TxDtTm")
        public String getTxDtTm() {
            return txDtTm;
        }

        @JsonProperty("TxDtTm")
        public void setTxDtTm(String txDtTm) {
            this.txDtTm = txDtTm;
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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Customer {
        private String givenName;
        private String surname;
        private String ip;

        public Customer() {
        }

        public String getGivenName() {
            return givenName;
        }

        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        @Override
        public String toString() {
            return "{givenName=" + givenName + ", surname=" + surname + ", ip=" + ip + "}";
        }

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "SIBSMULTIBANCO_PtmntEntty", "SIBSMULTIBANCO_RefIntlDtTm", "SIBSMULTIBANCO_RefLmtDtTm" })
    public static class CustomParameters {

        @JsonProperty("SIBSMULTIBANCO_PtmntEntty")
        private String sibsPaymentEntity;
        @JsonProperty("SIBSMULTIBANCO_RefIntlDtTm")
        private String sibsRefIntDate;
        @JsonProperty("SIBSMULTIBANCO_RefLmtDtTm")
        private String sibsRefLmtDate;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public CustomParameters() {
        }

        @JsonProperty("SIBSMULTIBANCO_PtmntEntty")
        public String getSibsPaymentEntity() {
            return sibsPaymentEntity;
        }

        @JsonProperty("SIBSMULTIBANCO_PtmntEntty")
        public void setSibsPaymentEntity(String sibsPaymentEntity) {
            this.sibsPaymentEntity = sibsPaymentEntity;
        }

        @JsonProperty("SIBSMULTIBANCO_RefIntlDtTm")
        public String getSibsRefIntDate() {
            return sibsRefIntDate;
        }

        @JsonProperty("SIBSMULTIBANCO_RefIntlDtTm")
        public void setSibsRefIntDate(String sibsRefIntDate) {
            this.sibsRefIntDate = sibsRefIntDate;
        }

        @JsonProperty("SIBSMULTIBANCO_RefLmtDtTm")
        public String getSibsRefLmtDate() {
            return sibsRefLmtDate;
        }

        @JsonProperty("SIBSMULTIBANCO_RefLmtDtTm")
        public void setSibsRefLmtDate(String sibsRefLmtDate) {
            this.sibsRefLmtDate = sibsRefLmtDate;
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
            return "{sibsPaymentEntity=" + sibsPaymentEntity + ", sibsRefIntDate=" + sibsRefIntDate + ", sibsRefLmtDate="
                    + sibsRefLmtDate + "}";
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Billing {
        private String country;

        public Billing() {
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "{country=" + country + "}";
        }

    }

    @JsonProperty("resultDetails")
    private ResultDetails resultDetails;
    private Customer customer;
    private CustomParameters customParameters;
    private Billing billing;
    private String paymentType;
    private String paymentBrand;
    private String amount;
    private String currency;
    private String descriptor;
    private String merchantTransactionId;
    private Result result;
    private String buildNumber;
    private String timestamp;
    private String ndc;
    private String id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("resultDetails")
    public ResultDetails getResultDetails() {
        return resultDetails;
    }

    @JsonProperty("resultDetails")
    public void setResultDetails(ResultDetails resultDetails) {
        this.resultDetails = resultDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomParameters getCustomParameters() {
        return customParameters;
    }

    public void setCustomParameters(CustomParameters customParameters) {
        this.customParameters = customParameters;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentBrand() {
        return paymentBrand;
    }

    public void setPaymentBrand(String paymentBrand) {
        this.paymentBrand = paymentBrand;
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

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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