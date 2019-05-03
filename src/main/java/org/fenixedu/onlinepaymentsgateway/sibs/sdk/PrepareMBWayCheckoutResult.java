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
@JsonPropertyOrder({ "id", "paymentType", "paymentBrand", "amount", "currency", "descriptor", "merchantTransactionId", "result",
        "resultDetails", "buildNumber", "timestamp", "ndc" })
public class PrepareMBWayCheckoutResult {

    public PrepareMBWayCheckoutResult() {
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
    @JsonPropertyOrder({ "Pre-authorization validity", "ConnectorTxID1", "ConnectorTxID3", "ConnectorTxID2", "AcquirerResponse" })
    public static class ResultDetails {

        @JsonProperty("Pre-authorization validity")
        private String preAuthorizationValidity;
        @JsonProperty("ConnectorTxID1")
        private String connectorTxID1;
        @JsonProperty("ConnectorTxID3")
        private String connectorTxID3;
        @JsonProperty("ConnectorTxID2")
        private String connectorTxID2;
        @JsonProperty("AcquirerResponse")
        private String acquirerResponse;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public ResultDetails() {
        }

        @JsonProperty("Pre-authorization validity")
        public String getPreAuthorizationValidity() {
            return preAuthorizationValidity;
        }

        @JsonProperty("Pre-authorization validity")
        public void setPreAuthorizationValidity(String preAuthorizationValidity) {
            this.preAuthorizationValidity = preAuthorizationValidity;
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

        @JsonProperty("ConnectorTxID2")
        public String getConnectorTxID2() {
            return connectorTxID2;
        }

        @JsonProperty("ConnectorTxID2")
        public void setConnectorTxID2(String connectorTxID2) {
            this.connectorTxID2 = connectorTxID2;
        }

        @JsonProperty("AcquirerResponse")
        public String getAcquirerResponse() {
            return acquirerResponse;
        }

        @JsonProperty("AcquirerResponse")
        public void setAcquirerResponse(String acquirerResponse) {
            this.acquirerResponse = acquirerResponse;
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
    @JsonProperty("resultDetails")
    private ResultDetails resultDetails;
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

    @JsonProperty("resultDetails")
    public ResultDetails getResultDetails() {
        return resultDetails;
    }

    @JsonProperty("resultDetails")
    public void setResultDetails(ResultDetails resultDetails) {
        this.resultDetails = resultDetails;
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
