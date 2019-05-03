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
public class PrepareCheckoutResult {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "code", "description" })
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

    @JsonProperty("result")
    private Result result;
    @JsonProperty("buildNumber")
    private String buildNumber;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("ndc")
    private String ndc;
    @JsonProperty("id")
    private String id;

    public PrepareCheckoutResult() {
        super();
    }

    @JsonProperty("result")
    public Result getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(Result result) {
        this.result = result;
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

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
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
