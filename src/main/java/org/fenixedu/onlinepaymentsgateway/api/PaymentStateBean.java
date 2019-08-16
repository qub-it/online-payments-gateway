package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PaymentType;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.SibsResultCodeType;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "paymentType", "paymentBrand", "amount", "currency", "descriptor", "merchantTransactionId",
        "merchantAccountId", "result", "resultDetails", "merchant", "customer", "authentication", "billing", "customParameters",
        "card", "threeDSecure", "risk", "redirect", "buildNumber", "timestamp", "ndc", "virtualAccount", "presentationAmount",
        "presentationCurrency", "referencedId" })
public class PaymentStateBean {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "entityId" })
    public static class Authentication {

        @JsonProperty("entityId")
        private String entityId;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    @JsonPropertyOrder({ "number", "bic", "country" })
    public static class BankAccount {

        @JsonProperty("number")
        private String number;
        @JsonProperty("bic")
        private String bic;
        @JsonProperty("country")
        private String country;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("number")
        public String getNumber() {
            return number;
        }

        @JsonProperty("number")
        public void setNumber(String number) {
            this.number = number;
        }

        @JsonProperty("bic")
        public String getBic() {
            return bic;
        }

        @JsonProperty("bic")
        public void setBic(String bic) {
            this.bic = bic;
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
    @JsonPropertyOrder({ "country" })
    public static class Billing {

        @JsonProperty("country")
        private String country;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

        @JsonProperty("bin")
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
    @JsonPropertyOrder({ "SIBSMULTIBANCO_PtmntEntty", "SIBSMULTIBANCO_RefLmtDtTm", "SIBSMULTIBANCO_RefIntlDtTm",
            "SHOPPER_EndToEndIdentity", "CTPE_DESCRIPTOR_TEMPLATE", "SIBS_ENV" })
    public static class CustomParameters {

        @JsonProperty("SIBSMULTIBANCO_PtmntEntty")
        private String sIBSMULTIBANCOPtmntEntty;
        @JsonProperty("SIBSMULTIBANCO_RefLmtDtTm")
        private String sIBSMULTIBANCORefLmtDtTm;
        @JsonProperty("SIBSMULTIBANCO_RefIntlDtTm")
        private String sIBSMULTIBANCORefIntlDtTm;
        @JsonProperty("SHOPPER_EndToEndIdentity")
        private String sHOPPEREndToEndIdentity;
        @JsonProperty("CTPE_DESCRIPTOR_TEMPLATE")
        private String cTPEDESCRIPTORTEMPLATE;
        @JsonProperty("SIBS_ENV")
        private String sIBSENV;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("SIBSMULTIBANCO_PtmntEntty")
        public String getSIBSMULTIBANCOPtmntEntty() {
            return sIBSMULTIBANCOPtmntEntty;
        }

        @JsonProperty("SIBSMULTIBANCO_PtmntEntty")
        public void setSIBSMULTIBANCOPtmntEntty(String sIBSMULTIBANCOPtmntEntty) {
            this.sIBSMULTIBANCOPtmntEntty = sIBSMULTIBANCOPtmntEntty;
        }

        @JsonProperty("SIBSMULTIBANCO_RefLmtDtTm")
        public String getSIBSMULTIBANCORefLmtDtTm() {
            return sIBSMULTIBANCORefLmtDtTm;
        }

        @JsonProperty("SIBSMULTIBANCO_RefLmtDtTm")
        public void setSIBSMULTIBANCORefLmtDtTm(String sIBSMULTIBANCORefLmtDtTm) {
            this.sIBSMULTIBANCORefLmtDtTm = sIBSMULTIBANCORefLmtDtTm;
        }

        @JsonProperty("SIBSMULTIBANCO_RefIntlDtTm")
        public String getSIBSMULTIBANCORefIntlDtTm() {
            return sIBSMULTIBANCORefIntlDtTm;
        }

        @JsonProperty("SIBSMULTIBANCO_RefIntlDtTm")
        public void setSIBSMULTIBANCORefIntlDtTm(String sIBSMULTIBANCORefIntlDtTm) {
            this.sIBSMULTIBANCORefIntlDtTm = sIBSMULTIBANCORefIntlDtTm;
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

        @JsonProperty("SIBS_ENV")
        public String getSIBSENV() {
            return sIBSENV;
        }

        @JsonProperty("SIBS_ENV")
        public void setSIBSENV(String sIBSENV) {
            this.sIBSENV = sIBSENV;
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
    @JsonPropertyOrder({ "givenName", "surname", "ip" })
    public static class Customer {

        @JsonProperty("givenName")
        private String givenName;
        @JsonProperty("surname")
        private String surname;
        @JsonProperty("ip")
        private String ip;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("givenName")
        public String getGivenName() {
            return givenName;
        }

        @JsonProperty("givenName")
        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

        @JsonProperty("surname")
        public String getSurname() {
            return surname;
        }

        @JsonProperty("surname")
        public void setSurname(String surname) {
            this.surname = surname;
        }

        @JsonProperty("ip")
        public String getIp() {
            return ip;
        }

        @JsonProperty("ip")
        public void setIp(String ip) {
            this.ip = ip;
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
    @JsonPropertyOrder({ "bankAccount" })
    public static class Merchant {

        @JsonProperty("bankAccount")
        private BankAccount bankAccount;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("bankAccount")
        public BankAccount getBankAccount() {
            return bankAccount;
        }

        @JsonProperty("bankAccount")
        public void setBankAccount(BankAccount bankAccount) {
            this.bankAccount = bankAccount;
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

        @JsonProperty("parameters")
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
    @JsonPropertyOrder({ "refLmtDtTm", "amount", "ptmntEntty", "ConnectorTxID1", "RcptTxId", "ConnectorTxID3", "ConnectorTxID2",
            "pmtRef", "uuid", "pmtRefNtty", "ExtendedDescription", "AcquirerResponse", "TxDtTm", "Pre-authorization validity",
            "clearingInstituteName" })
    public static class ResultDetails {
        @JsonProperty("refLmtDtTm")
        private String refLmtDtTm;
        @JsonProperty("amount")
        private String amount;
        @JsonProperty("ptmntEntty")
        private String ptmntEntty;
        @JsonProperty("ConnectorTxID1")
        private String connectorTxID1;
        @JsonProperty("RcptTxId")
        private String rcptTxId;
        @JsonProperty("ConnectorTxID3")
        private String connectorTxID3;
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
        @JsonProperty("Pre-authorization validity")
        private String preAuthorizationValidity;
        @JsonProperty("clearingInstituteName")
        private String clearingInstituteName;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

        @JsonProperty("RcptTxId")
        public String getRcptTxId() {
            return rcptTxId;
        }

        @JsonProperty("RcptTxId")
        public void setRcptTxId(String rcptTxId) {
            this.rcptTxId = rcptTxId;
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

        @JsonProperty("ExtendedDescription")
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

        @JsonProperty("Pre-authorization validity")
        public String getPreAuthorizationValidity() {
            return preAuthorizationValidity;
        }

        @JsonProperty("Pre-authorization validity")
        public void setPreAuthorizationValidity(String preAuthorizationValidity) {
            this.preAuthorizationValidity = preAuthorizationValidity;
        }

        @JsonProperty("clearingInstituteName")
        public String getClearingInstituteName() {
            return clearingInstituteName;
        }

        @JsonProperty("clearingInstituteName")
        public void setClearingInstituteName(String clearingInstituteName) {
            this.clearingInstituteName = clearingInstituteName;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "accountId" })
    public static class VirtualAccount {

        @JsonProperty("accountId")
        private String accountId;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("accountId")
        public String getAccountId() {
            return accountId;
        }

        @JsonProperty("accountId")
        public void setAccountId(String accountId) {
            this.accountId = accountId;
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
    private String transactionId;
    @JsonProperty("paymentType")
    private String paymentType;
    @JsonProperty("paymentBrand")
    private String paymentBrand;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("descriptor")
    private String descriptor;
    @JsonProperty("merchantTransactionId")
    private String merchantTransactionId;
    @JsonProperty("merchantAccountId")
    private String merchantAccountId;
    @JsonProperty("result")
    private Result result;
    @JsonProperty("resultDetails")
    private ResultDetails resultDetails;
    @JsonProperty("merchant")
    private Merchant merchant;
    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("authentication")
    private Authentication authentication;
    @JsonProperty("billing")
    private Billing billing;
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
    @JsonProperty("virtualAccount")
    private VirtualAccount virtualAccount;

    @JsonProperty("presentationAmount")
    private String presentationAmount;
    @JsonProperty("presentationCurrency")
    private String presentationCurrency;
    @JsonProperty("redirect")
    private Redirect redirect;
    @JsonProperty("referencedId")
    private String referencedId;

    private String requestLog;
    private String responseLog;
    private SibsResultCodeType operationResultType;
    private String operationResultDescription;
    private String paymentState;
    private DateTime paymentDate;
    private String notificationType;

    private Exception exception;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public PaymentStateBean() {
    }

    public boolean isPaid() {
        return this.operationResultType.isPaid() && (PaymentType.DB.name().equals(getPaymentType()) || PaymentType.RC.name().equals(getPaymentType()));
    }

    public boolean isOperationSuccess() {
        return this.operationResultType.isSuccess();
    }

    public DateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(DateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentGatewayResultCode() {
        return getResult().getCode();
    }

    public String getPaymentGatewayResultDescription() {
        return getResult().getDescription();
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    @JsonProperty("id")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("id")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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
    public BigDecimal getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(BigDecimal amount) {
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

    @JsonProperty("merchant")
    public Merchant getMerchant() {
        return merchant;
    }

    @JsonProperty("merchant")
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    @JsonProperty("customer")
    public Customer getCustomer() {
        return customer;
    }

    @JsonProperty("customer")
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @JsonProperty("billing")
    public Billing getBilling() {
        return billing;
    }

    @JsonProperty("billing")
    public void setBilling(Billing billing) {
        this.billing = billing;
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

    @JsonProperty("virtualAccount")
    public VirtualAccount getVirtualAccount() {
        return virtualAccount;
    }

    @JsonProperty("virtualAccount")
    public void setVirtualAccount(VirtualAccount virtualAccount) {
        this.virtualAccount = virtualAccount;
    }

    public String getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(String merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public String getPresentationAmount() {
        return presentationAmount;
    }

    public void setPresentationAmount(String presentationAmount) {
        this.presentationAmount = presentationAmount;
    }

    public String getPresentationCurrency() {
        return presentationCurrency;
    }

    public void setPresentationCurrency(String presentationCurrency) {
        this.presentationCurrency = presentationCurrency;
    }

    public Redirect getRedirect() {
        return redirect;
    }

    public void setRedirect(Redirect redirect) {
        this.redirect = redirect;
    }

    public String getReferencedId() {
        return referencedId;
    }

    public void setReferencedId(String referencedId) {
        this.referencedId = referencedId;
    }

    public String getRequestLog() {
        return requestLog;
    }

    public void setRequestLog(String requestLog) {
        this.requestLog = requestLog;
    }

    public String getResponseLog() {
        return responseLog;
    }

    public void setResponseLog(String responseLog) {
        this.responseLog = responseLog;
    }

    public SibsResultCodeType getOperationResultType() {
        return operationResultType;
    }

    public void setOperationResultType(SibsResultCodeType operationResultType) {
        this.operationResultType = operationResultType;
    }

    public String getOperationResultDescription() {
        return operationResultDescription;
    }

    public void setOperationResultDescription(String operationResultDescription) {
        this.operationResultDescription = operationResultDescription;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
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
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}