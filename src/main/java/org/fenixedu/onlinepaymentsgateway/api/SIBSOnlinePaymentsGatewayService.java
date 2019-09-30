package org.fenixedu.onlinepaymentsgateway.api;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.fenixedu.onlinepaymentsgateway.exceptions.OnlinePaymentsGatewayCommunicationException;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.MerchantIdReportBean;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.MerchantIdReportBean.Payment;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.NotificationBean;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PaymentBrand;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PaymentType;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareCheckout;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareMBCheckout;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareMBCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareMBWayCheckout;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareMBWayCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.SibsResultCodeType;
import org.fenixedu.onlinepaymentsgateway.util.Decryption;
import org.glassfish.jersey.logging.LoggingFeature;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SIBSOnlinePaymentsGatewayService {

    private static Logger logger = LoggerFactory.getLogger(SIBSOnlinePaymentsGatewayService.class);
    private Feature feature = new LoggingFeature(
            java.util.logging.Logger.getLogger(SIBSOnlinePaymentsGatewayService.class.getName()), Level.FINEST, null, null);
    private Client client = ClientBuilder.newBuilder().register(feature).build();
    private WebTarget webTargetBase;
    private SIBSInitializeServiceBean initializeServiceBean;
    private static final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss+SSSS");
    //DateTimeFormatter formatterPaymentDate = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");//TODO backup diferente dateformat

    public SIBSOnlinePaymentsGatewayService(SIBSInitializeServiceBean initializeServiceBean) {
        this.initializeServiceBean = initializeServiceBean;
        if (initializeServiceBean.getEndpointUrl() != null) {
            this.webTargetBase = client.target(initializeServiceBean.getEndpointUrl());
        }
    }

    //Create multibanco payment with server-to-server
    public MbCheckoutResultBean generateMBPaymentReference(MbPrepareCheckoutInputBean mbPrepareCheckoutInputBean,
            CustomerDataInputBean customerInputBean) throws OnlinePaymentsGatewayCommunicationException {
        if (!this.initializeServiceBean.isAuthPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service Authentication");
        }
        if (!this.initializeServiceBean.isPaymentPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service payment settings");
        }
        if (mbPrepareCheckoutInputBean == null || !mbPrepareCheckoutInputBean.isPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Amount or Dates");
        }
        if (mbPrepareCheckoutInputBean.getAmount().scale() > 2) {
            throw new IllegalArgumentException("Invalid Amount, Amount.scale() <= 2");
        }

        WebTarget target = webTargetBase.path("payments");

        final String bearerToken = this.initializeServiceBean.getBearerToken();
        final String entityId = this.initializeServiceBean.getEntityId();
        final String paymentCurrency = this.initializeServiceBean.getPaymentCurrency();
        final String paymentEntity = this.initializeServiceBean.getPaymentEntity();
        final String paymentType = PaymentType.PA.name();
        final String paymentBrand = "SIBS_MULTIBANCO";
        final String billingCountry = "PT";
        final String paymentAmount = mbPrepareCheckoutInputBean.getAmount().setScale(2, RoundingMode.HALF_EVEN).toString();

        final String sibsRefIntDate = mbPrepareCheckoutInputBean.getSibsRefIntDate().toString(ISODateTimeFormat.dateTime());
        final String sibsRefLmtDate = mbPrepareCheckoutInputBean.getSibsRefLmtDate().toString(ISODateTimeFormat.dateTime());

        final PrepareMBCheckout mbPrepCheckout = new PrepareMBCheckout(entityId, paymentAmount, paymentCurrency, paymentType,
                paymentBrand, paymentEntity, sibsRefIntDate, sibsRefLmtDate, billingCountry, customerInputBean,
                this.initializeServiceBean.getEnvironmentMode());

        if (mbPrepareCheckoutInputBean.getMerchantTransactionId() != null) {
            String merchantTransactionId = mbPrepareCheckoutInputBean.getMerchantTransactionId();
            mbPrepCheckout.setMerchantTransactionId(merchantTransactionId);
        }

        final String requestLog = mbPrepCheckout.toString();
        Form form = new Form(mbPrepCheckout.asMap());
        String responseLog = null;
        Builder builder = target.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);

        try {
            responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).post(Entity.form(form), String.class);
            PrepareMBCheckoutResult result = customMapper(responseLog, PrepareMBCheckoutResult.class);

            SibsResultCodeType operationResultType = validateSibsResult(result.getResult().getCode());
            String operationResultDescription =
                    operationResultDescription(result.getResult().getDescription(), operationResultType);

            //components that might not exist need an IF
            MbCheckoutResultBean mbCheckoutResult = new MbCheckoutResultBean(result.getId(), result.getMerchantTransactionId(),
                    DateTime.parse(result.getTimestamp(), formatter), result.getAmount(), result.getCurrency(),
                    result.getPaymentBrand(), result.getPaymentType(), result.getCustomParameters().getSibsPaymentEntity(),
                    result.getResultDetails().getPmtRef(), result.getCustomParameters().getSibsRefIntDate(),
                    result.getCustomParameters().getSibsRefLmtDate(), operationResultType, operationResultDescription,
                    result.getResult().getCode(), result.getResult().getDescription());

            if (result.getMerchantTransactionId() != null) {
                mbCheckoutResult.setMerchantTransactionId(result.getMerchantTransactionId());
            }

            mbCheckoutResult.setRequestLog(requestLog);
            mbCheckoutResult.setResponseLog(responseLog);

            boolean validAnswer = true;
            validAnswer &= mbPrepCheckout.getAmount() != null && mbCheckoutResult.getAmount() != null
                    && mbPrepCheckout.getAmount().equals(mbCheckoutResult.getAmount().toString());
            validAnswer &= mbPrepCheckout.getCurrency() != null && mbCheckoutResult.getCurrency() != null
                    && mbPrepCheckout.getCurrency().equals(mbCheckoutResult.getCurrency());
            validAnswer &= mbPrepCheckout.getPaymentBrand() != null && mbCheckoutResult.getPaymentBrand() != null
                    && mbPrepCheckout.getPaymentBrand().equals(mbCheckoutResult.getPaymentBrand());
            validAnswer &= mbPrepCheckout.getPaymentType() != null && mbCheckoutResult.getPaymentType() != null
                    && mbPrepCheckout.getPaymentType().equals(mbCheckoutResult.getPaymentType());
            if (mbPrepCheckout.getMerchantTransactionId() != null) {
                validAnswer &= mbCheckoutResult.getMerchantTransactionId() != null
                        && mbPrepCheckout.getMerchantTransactionId().equals(mbCheckoutResult.getMerchantTransactionId());
            }
            if (!validAnswer) {
                throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog,
                        "Request and Response details do not match.");
            }

            logger.debug(mbCheckoutResult.toString());

            return mbCheckoutResult;

        } catch (WebApplicationException e) {
            responseLog = e.getResponse().readEntity(String.class);
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        } catch (Exception e) {
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        }
    }

    //Create mbway payment with server-to-server
    public MbWayCheckoutResultBean generateMbWayPayment(MbWayPrepareCheckoutInputBean mbwayPrepareCheckoutInputBean,
            CustomerDataInputBean customerInputBean) throws OnlinePaymentsGatewayCommunicationException {
        if (!this.initializeServiceBean.isAuthPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service Authentication");
        }
        if (!this.initializeServiceBean.isPaymentPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service payment settings");
        }
        if (mbwayPrepareCheckoutInputBean == null || !mbwayPrepareCheckoutInputBean.isAmountValid()) {
            throw new IllegalArgumentException("Invalid Payment input");
        }
        if (mbwayPrepareCheckoutInputBean.getAmount().scale() > 2) {
            throw new IllegalArgumentException("Invalid Amount, Amount.scale() <= 2");
        }
        if (mbwayPrepareCheckoutInputBean == null || !mbwayPrepareCheckoutInputBean.isNumberValid()) {
            throw new IllegalArgumentException("Invalid Phone Number input");
        }

        WebTarget target = webTargetBase.path("payments");

        final String bearerToken = this.initializeServiceBean.getBearerToken();
        final String entityId = this.initializeServiceBean.getEntityId();
        final String paymentCurrency = this.initializeServiceBean.getPaymentCurrency();
        final String paymentType = PaymentType.DB.name();
        final String paymentBrand = "MBWAY";
        final String paymentAmount = mbwayPrepareCheckoutInputBean.getAmount().setScale(2, RoundingMode.HALF_EVEN).toString();
        final String phoneNumber = mbwayPrepareCheckoutInputBean.getPhoneNumber();

        final PrepareMBWayCheckout mbwayPrepCheckout = new PrepareMBWayCheckout(entityId, paymentAmount, paymentCurrency,
                paymentType, paymentBrand, phoneNumber, customerInputBean, this.initializeServiceBean.getEnvironmentMode());

        if (mbwayPrepareCheckoutInputBean.getMerchantTransactionId() != null) {
            String merchantTransactionId = mbwayPrepareCheckoutInputBean.getMerchantTransactionId();
            mbwayPrepCheckout.setMerchantTransactionId(merchantTransactionId);
        }

        final String requestLog = mbwayPrepCheckout.toString();
        Form form = new Form(mbwayPrepCheckout.asMap());
        String responseLog = null;
        Builder builder = target.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);

        //try { how to handle error 400, test with customParameters on mbway
        try {
            responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).post(Entity.form(form), String.class);
            PrepareMBWayCheckoutResult result = customMapper(responseLog, PrepareMBWayCheckoutResult.class);

            String answerContainingPhoneNumber = result.getResultDetails().getConnectorTxID3();
            String phoneNumberResult = answerContainingPhoneNumber.substring(answerContainingPhoneNumber.lastIndexOf("||") + 2,
                    answerContainingPhoneNumber.lastIndexOf("||") + 15);

            SibsResultCodeType operationResultType = validateSibsResult(result.getResult().getCode());
            String operationResultDescription =
                    operationResultDescription(result.getResult().getDescription(), operationResultType);

            MbWayCheckoutResultBean mbwayCheckoutResult = new MbWayCheckoutResultBean(result.getId(),
                    DateTime.parse(result.getTimestamp(), formatter), new BigDecimal(result.getAmount()), result.getCurrency(),
                    result.getPaymentBrand(), result.getPaymentType(), phoneNumberResult,
                    result.getResultDetails().getAcquirerResponse(), operationResultType, operationResultDescription,
                    result.getResult().getCode(), result.getResult().getDescription());

            if (result.getMerchantTransactionId() != null) {
                mbwayCheckoutResult.setMerchantTransactionId(result.getMerchantTransactionId());
            }

            mbwayCheckoutResult.setRequestLog(requestLog);
            mbwayCheckoutResult.setResponseLog(responseLog);

            boolean validAnswer = true;
            validAnswer &= mbwayPrepCheckout.getAmount() != null && mbwayCheckoutResult.getAmount() != null
                    && mbwayPrepCheckout.getAmount().equals(mbwayCheckoutResult.getAmount().toString());
            validAnswer &= mbwayPrepCheckout.getCurrency() != null && mbwayCheckoutResult.getCurrency() != null
                    && mbwayPrepCheckout.getCurrency().equals(mbwayCheckoutResult.getCurrency());
            validAnswer &= mbwayPrepCheckout.getPaymentBrand() != null && mbwayCheckoutResult.getPaymentBrand() != null
                    && mbwayPrepCheckout.getPaymentBrand().equals(mbwayCheckoutResult.getPaymentBrand());
            validAnswer &= mbwayPrepCheckout.getPaymentType() != null && mbwayCheckoutResult.getPaymentType() != null
                    && mbwayPrepCheckout.getPaymentType().equals(mbwayCheckoutResult.getPaymentType());
            if (mbwayPrepCheckout.getMerchantTransactionId() != null) {
                validAnswer &= mbwayCheckoutResult.getMerchantTransactionId() != null
                        && mbwayPrepCheckout.getMerchantTransactionId().equals(mbwayCheckoutResult.getMerchantTransactionId());
            }
            if (!validAnswer) {
                throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog,
                        "Request and Response details do not match.");
            }

            return mbwayCheckoutResult;

        } catch (WebApplicationException e) {
            responseLog = e.getResponse().readEntity(String.class);
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        } catch (Exception e) {
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        }

    }

    public CheckoutResultBean prepareOnlinePaymentCheckout(PrepareCheckoutInputBean prepareCheckoutInputBean)
            throws OnlinePaymentsGatewayCommunicationException {
        if (!this.initializeServiceBean.isAuthPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service Authentication");
        }
        if (!this.initializeServiceBean.isPaymentPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service payment settings");
        }
        if (prepareCheckoutInputBean == null || !(prepareCheckoutInputBean.getUseMB() ? prepareCheckoutInputBean
                .isMBPropertiesValid() : prepareCheckoutInputBean.isPropertiesValid())) {
            throw new IllegalArgumentException("Invalid Payment input");
        }
        if (prepareCheckoutInputBean.getAmount().scale() > 2) {
            throw new IllegalArgumentException("Amount.scale() <= 2");
        }

        WebTarget target = webTargetBase.path("checkouts");

        final String bearerToken = this.initializeServiceBean.getBearerToken();
        final String entityId = this.initializeServiceBean.getEntityId();
        final String paymentCurrency = this.initializeServiceBean.getPaymentCurrency();
        final String paymentEntity = this.initializeServiceBean.getPaymentEntity();

        final Boolean useCreditCard = prepareCheckoutInputBean.getUseCreditCard();
        final Boolean useMBway = prepareCheckoutInputBean.getUseMBway();
        final Boolean useMB = prepareCheckoutInputBean.getUseMB();

        final String paymentType = useMB ? PaymentType.PA.name() : PaymentType.DB.name();

        final String billingCountry = "PT";
        final BigDecimal paymentAmount = prepareCheckoutInputBean.getAmount().setScale(2, RoundingMode.HALF_EVEN);

        final String shopperResultUrl = prepareCheckoutInputBean.getShopperResultUrl();

        PrepareCheckout prepCheckout = new PrepareCheckout(entityId, paymentAmount.toString(), paymentCurrency, paymentType,
                billingCountry, this.initializeServiceBean.getEnvironmentMode());

        if (useMB) {
            final String sibsRefIntDate = prepareCheckoutInputBean.getSibsRefIntDate().toString();
            final String sibsRefLmtDate = prepareCheckoutInputBean.getSibsRefLmtDate().toString();
            prepCheckout.setSIBSMULTIBANCO_PtmntEntty(paymentEntity);
            prepCheckout.setSIBSMULTIBANCO_RefIntlDtTm(sibsRefIntDate);
            prepCheckout.setSIBSMULTIBANCO_RefLmtDtTm(sibsRefLmtDate);
        }

        if (prepareCheckoutInputBean.getMerchantTransactionId() != null) {
            final String merchantTransactionId = prepareCheckoutInputBean.getMerchantTransactionId();
            prepCheckout.setMerchantTransactionId(merchantTransactionId);
        }

        final String requestLog = prepCheckout.toString();
        Form form = new Form(prepCheckout.asMap());
        String responseLog = null;
        Builder builder = target.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);

        //try { how to handle because can't have responseLog - how to handle httpbadrequest...
        try {
            responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).post(Entity.form(form), String.class);

            PrepareCheckoutResult result = customMapper(responseLog, PrepareCheckoutResult.class);
            SibsResultCodeType operationResultType = validateSibsResult(result.getResult().getCode());
            String operationResultDescription =
                    operationResultDescription(result.getResult().getDescription(), operationResultType);

            CheckoutResultBean checkoutResult =
                    new CheckoutResultBean(result.getId(), DateTime.parse(result.getTimestamp(), formatter), shopperResultUrl,
                            paymentAmount, paymentCurrency, operationResultType, operationResultDescription,
                            result.getResult().getCode(), result.getResult().getDescription());

            checkoutResult.setRequestLog(requestLog);
            checkoutResult.setResponseLog(responseLog);

            String paymentBrands = "";
            if (useCreditCard) {
                paymentBrands +=
                        " " + PaymentBrand.VISA.name() + " " + PaymentBrand.MASTER.name() + " " + PaymentBrand.AMEX.name();
            }
            if (useMBway) {
                paymentBrands += " " + PaymentBrand.MBWAY.name();
            }
            if (useMB) {
                paymentBrands = PaymentBrand.SIBS_MULTIBANCO.name();
            }
            paymentBrands = paymentBrands.trim();

            checkoutResult.setPaymentBrands(paymentBrands);

            return checkoutResult;

        } catch (WebApplicationException e) {
            responseLog = e.getResponse().readEntity(String.class);
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        }

    }

    //Creates the forms needed for copy and pay
    public PaymentFormBean createOnlinePaymentForm(CheckoutResultBean checkoutResultBean) throws Exception {
        //assert (checkoutResultBean != null);
        String endpointUrl = initializeServiceBean.getEndpointUrl();
        String checkoutId = checkoutResultBean.getCheckoutId();
        String shopperResultUrl = checkoutResultBean.getShopperResultUrl();
        String paymentBrands = checkoutResultBean.getPaymentBrands();

        String htmlScript = "<script src='" + endpointUrl + "/paymentWidgets.js?checkoutId=" + checkoutId + "'></script>";
        String htmlForm =
                "<form action='" + shopperResultUrl + "' class='paymentWidgets' data-brands='" + paymentBrands + "'></form>";

        PaymentFormBean paymentForm = new PaymentFormBean(htmlScript, htmlForm);

        return paymentForm;
    }

    //Get result from copyandpay
    public PaymentStateBean getPaymentStatusByCheckoutId(String checkoutId) throws OnlinePaymentsGatewayCommunicationException {
        if (!this.initializeServiceBean.isAuthPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service Authentication");
        }
        if (checkoutId == null || checkoutId.isEmpty()) {
            throw new IllegalArgumentException("Invalid Checkout Id");
        }

        WebTarget target = webTargetBase.path("checkouts/" + checkoutId + "/payment");

        final String bearerToken = this.initializeServiceBean.getBearerToken();
        final String entityId = this.initializeServiceBean.getEntityId();

        Builder builder = target.queryParam("entityId", entityId).request("application/x-www-form-urlencoded; charset=UTF-8")
                .accept(MediaType.APPLICATION_JSON);

        final String requestLog =
                "Entity Id = " + entityId + ", Authorization = " + bearerToken + ", Checkout Id = " + checkoutId;

        //try { how to handle because can't have responseLog - how to handle httpbadrequest..
        String responseLog = null;
        try {
            responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).get(String.class);
            PaymentStateBean transactionStatus = customMapper(responseLog, PaymentStateBean.class);
            SibsResultCodeType operationResultType = validateSibsResult(transactionStatus.getResult().getCode());
            String operationResultDescription =
                    operationResultDescription(transactionStatus.getResult().getDescription(), operationResultType);

            transactionStatus.setOperationResultType(operationResultType);
            transactionStatus.setOperationResultDescription(operationResultDescription);
            transactionStatus.setRequestLog(requestLog);
            transactionStatus.setResponseLog(responseLog);

            transactionStatus.setPaymentDate(DateTime.parse(transactionStatus.getTimestamp(), formatter));

            /*PaymentStatusBean paymentStatusBean = new PaymentStatusBean(transactionStatus.getId(), //2019-07-10 18:40:34+0000
                    DateTime.parse(transactionStatus.getTimestamp(), formatter).toDateTime(), transactionStatus.getPaymentType(),
                    transactionStatus.getPaymentBrand(), transactionStatus.getAmount(), transactionStatus.getCurrency(),
                    DateTime.parse(transactionStatus.getResultDetails().getConnectorTxID2(), formatter2).toDateTime(),
                    transactionStatus.getResult().getCode(), transactionStatus.getResult().getDescription(), operationResultType,
                    operationResultDescription);
            
            paymentStatusBean.setRequestLog(requestLog);
            paymentStatusBean.setResponseLog(responseLog);*/

            return transactionStatus;

        } catch (WebApplicationException e) {
            responseLog = e.getResponse().readEntity(String.class);
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        }
    }

    //Get transaction report from transaction ID
    public PaymentStateBean getPaymentTransactionReportByTransactionId(String transactionId)
            throws OnlinePaymentsGatewayCommunicationException {
        if (!this.initializeServiceBean.isAuthPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service Authentication");
        }
        if (transactionId == null || transactionId.isEmpty()) {
            throw new IllegalArgumentException("Invalid Transaction Id");
        }

        WebTarget target = webTargetBase.path("query/" + transactionId);

        final String bearerToken = this.initializeServiceBean.getBearerToken();
        final String entityId = this.initializeServiceBean.getEntityId();

        final String requestLog =
                "Entity Id = " + entityId + ", Authorization = " + bearerToken + ", Transaction Id = " + transactionId;

        String responseLog = null;

        Builder builder = target.queryParam("entityId", entityId).request("application/x-www-form-urlencoded; charset=UTF-8")
                .accept(MediaType.APPLICATION_JSON);

        try {
            responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).get(String.class);
            PaymentStateBean transactionReport = customMapper(responseLog, PaymentStateBean.class);

            SibsResultCodeType operationResultType = validateSibsResult(transactionReport.getResult().getCode());
            String operationResultDescription =
                    operationResultDescription(transactionReport.getResult().getDescription(), operationResultType);

            transactionReport.setOperationResultType(operationResultType);
            transactionReport.setOperationResultDescription(operationResultDescription);
            transactionReport.setRequestLog(requestLog);
            transactionReport.setResponseLog(responseLog);

            transactionReport.setPaymentDate(DateTime.parse(transactionReport.getTimestamp(), formatter));

            boolean validAnswer = true;
            validAnswer &=
                    transactionReport.getTransactionId() != null && transactionReport.getTransactionId().equals(transactionId);
            if (!validAnswer) {
                throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog,
                        "Request and Response details do not match.");
            }

            return transactionReport;

        } catch (WebApplicationException e) {
            responseLog = e.getResponse().readEntity(String.class);
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        } catch (Exception e) {
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        }
    }

    @Deprecated
    // The last transaction may not be a payment. The payment state status must be presented and analysed 
    // with each transaction associated with merchant transaction id
    public PaymentStateBean getPaymentTransactionReportByMerchantId(String merchantTransactionId)
            throws OnlinePaymentsGatewayCommunicationException {
        if (!this.initializeServiceBean.isAuthPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service Authentication");
        }
        if (merchantTransactionId == null || merchantTransactionId.isEmpty()) {
            throw new IllegalArgumentException("Invalid Merchant Transaction Id");
        }

        WebTarget target = webTargetBase.path("query");

        final String bearerToken = this.initializeServiceBean.getBearerToken();
        final String entityId = this.initializeServiceBean.getEntityId();

        final String requestLog = "Entity Id = " + entityId + ", Authorization = " + bearerToken + ", Merchant Transaction Id = "
                + merchantTransactionId;

        String responseLog = null;

        Builder builder = target.queryParam("entityId", entityId).queryParam("merchantTransactionId", merchantTransactionId)
                .request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);

        try {
            responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).get(String.class);
            MerchantIdReportBean merchantReport = customMapper(responseLog, MerchantIdReportBean.class);

            String lastPayment = merchantReport.getPayments().get(merchantReport.getPayments().size() - 1).toString();

            PaymentStateBean transactionReport = customMapper(lastPayment, PaymentStateBean.class);

            SibsResultCodeType operationResultType = validateSibsResult(transactionReport.getResult().getCode());
            String operationResultDescription =
                    operationResultDescription(transactionReport.getResult().getDescription(), operationResultType);

            transactionReport.setOperationResultType(operationResultType);
            transactionReport.setOperationResultDescription(operationResultDescription);
            transactionReport.setRequestLog(requestLog);
            transactionReport.setResponseLog(responseLog);

            transactionReport.setPaymentDate(DateTime.parse(transactionReport.getTimestamp(), formatter));

            boolean validAnswer = true;
            validAnswer &= transactionReport.getMerchantTransactionId() != null
                    && transactionReport.getMerchantTransactionId().equals(merchantTransactionId);
            if (!validAnswer) {
                throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog,
                        "Request and Response details do not match.");
            }

            return transactionReport;

        } catch (WebApplicationException e) {
            responseLog = e.getResponse().readEntity(String.class);
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        } catch (Exception e) {
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        }
    }

    
    public List<PaymentStateBean> getPaymentTransactionsReportListByMerchantId(String merchantTransactionId) throws OnlinePaymentsGatewayCommunicationException {
        if (!this.initializeServiceBean.isAuthPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service Authentication");
        }
        if (merchantTransactionId == null || merchantTransactionId.isEmpty()) {
            throw new IllegalArgumentException("Invalid Merchant Transaction Id");
        }

        WebTarget target = webTargetBase.path("query");

        final String bearerToken = this.initializeServiceBean.getBearerToken();
        final String entityId = this.initializeServiceBean.getEntityId();

        final String requestLog = "Entity Id = " + entityId + ", Authorization = " + bearerToken + ", Merchant Transaction Id = "
                + merchantTransactionId;

        String responseLog = null;

        Builder builder = target.queryParam("entityId", entityId).queryParam("merchantTransactionId", merchantTransactionId)
                .request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);

        try {
            responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).get(String.class);
            MerchantIdReportBean merchantReport = customMapper(responseLog, MerchantIdReportBean.class);
            
            final List<PaymentStateBean> result = new ArrayList<>();
            for (final Payment payment : merchantReport.getPayments()) {
                String lastPayment = payment.toString();

                PaymentStateBean transactionReport = customMapper(lastPayment, PaymentStateBean.class);

                SibsResultCodeType operationResultType = validateSibsResult(transactionReport.getResult().getCode());
                String operationResultDescription =
                        operationResultDescription(transactionReport.getResult().getDescription(), operationResultType);

                transactionReport.setOperationResultType(operationResultType);
                transactionReport.setOperationResultDescription(operationResultDescription);
                transactionReport.setRequestLog(requestLog);
                transactionReport.setResponseLog(responseLog);

                transactionReport.setPaymentDate(DateTime.parse(transactionReport.getTimestamp(), formatter));

                boolean validAnswer = true;
                validAnswer &= transactionReport.getMerchantTransactionId() != null
                        && transactionReport.getMerchantTransactionId().equals(merchantTransactionId);
                if (!validAnswer) {
                    throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog,
                            "Request and Response details do not match.");
                }
                
                result.add(transactionReport);
            }

            return result;
        } catch (WebApplicationException e) {
            responseLog = e.getResponse().readEntity(String.class);
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        } catch (Exception e) {
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, responseLog, e);
        }
    }

    //TODO Juntar ao PaymentStateBean, verificar como lidar com "Type" e "Payload"
    public PaymentStateBean handleNotificationRequest(String initializationVector, String authTag, String encryptedPayload)
            throws Exception {
        logger.debug("Request encrypted: " + encryptedPayload.toString());
        String decryptedPayload = null;
        try {
            Decryption notification =
                    new Decryption(this.initializeServiceBean.getAesKey(), initializationVector, authTag, encryptedPayload);

            decryptedPayload = notification.decryptPayload();
            logger.debug("\n Request desencrypted: " + decryptedPayload);

            //TODO validate result with OnlinePaymentOperationResultType && validateSibsResult
            NotificationBean payload = customMapper(decryptedPayload, NotificationBean.class);
            logger.debug("\n Payload content: " + payload.toString());

            String payloadOnly = payload.getPayload().toJson();

            final PaymentStateBean notificationReport = customMapper(payloadOnly, PaymentStateBean.class);
            notificationReport.setNotificationType(payload.getType().toString());
            
            final SibsResultCodeType operationResultType = validateSibsResult(notificationReport.getResult().getCode());
            final String operationResultDescription =
                    operationResultDescription(notificationReport.getResult().getDescription(), operationResultType);

            notificationReport.setOperationResultType(operationResultType);
            notificationReport.setOperationResultDescription(operationResultDescription);

            notificationReport.setRequestLog(decryptedPayload);

            notificationReport.setPaymentDate(DateTime.parse(notificationReport.getTimestamp(), formatter));

            return notificationReport;
        } catch (Exception e) {
            throw new OnlinePaymentsGatewayCommunicationException(decryptedPayload, null, e);
        }

    }

    public PaymentStateBean handleNotificationServletRequest(HttpServletRequest httpServletRequest) throws Exception {
        String initializationVector = notificationInitializationVector(httpServletRequest);
        String authTag = notificationAuthenticationTag(httpServletRequest);
        String encryptedPayload = notificationEncryptedPayload(httpServletRequest);

        PaymentStateBean payload = handleNotificationRequest(initializationVector, authTag, encryptedPayload);

        return payload;
    }
    
    public static String notificationEncryptedPayload(final HttpServletRequest request) throws Exception {
        return request.getReader().readLine();
    }
    
    public static String notificationInitializationVector(final HttpServletRequest request) {
        return request.getHeader("X-Initialization-Vector");
    }
    
    public static String notificationAuthenticationTag(final HttpServletRequest request) {
        return request.getHeader("X-Authentication-Tag");
    }

    /*Content-Length: 2454
    X-Initialization-Vector: 33FF841C5FFCB95D3BA9593A
    X-Authentication-Tag: 3DBF9FC2DCA65F737AF410A4A95DD610
    Content-Type: text/plain; charset=ISO-8859-1
    User-Agent: Apache-HttpAsyncClient/4.1.1 (Java/1.8.0_172)
    
    827906EE330B988F4EE254D748FD07D2FFC5116D6EDF0B7277A45033A28EAE1FC04DBF1A08B014BAB243FFC7CD0A313AECC9CE9B9A146F6E59DCF104867D78428E66031D516806B8FD84E325A6257F9B38F7F09C56ECB9095CC194091A589AE66D95291EB6136029393E1F7B4C0AB67F0F0628AFB90B7C10D296013A5EDF2658897575090F291521235738AE6742B744AED22541F3EF490031A7BD873436BFA83DBB8979744437D957F61032F592C06A03EB075CEDB1B7785205C7DD69F792B6EBE12401E75CE6A6DE7C113DDDC8CB6731F47025512AFD5B0787CDB063B50E5A2352A9CC49906AA360C44D4AF0A9AC86C7296AA91682EA4E255C104A68914DA00881E1B4B5DDD1E1A7A78EF8F044105C9377919978F0EE0DC670877EEDF884C0155C07FB9089FDC0A5DBB960655D9FA58EFE606BA1D29D09FD72CFBE6CFD8431D0D308798E22F93E6BA0C134DFF79E1B2C3F4CC32A7E141DCC191EBF5EF8A58EF580D9270EFD8155FF528CB2EE2AF057A289BFB9BC9CEDF2B75A7DADE2E9DD5D16CEBA0BFEC002D0BF947C4C9C4F0E11D065E9004B1164BF7412A39936BECE1268F2F200C9C0A48407DD2B15810681DB707E143B530F42B37E26D396D24DF1BD0DFD7FC4F82A9B9C2126BC6DAD3FAF7F97E037A0466478A0CE3BB324563A2882F250B820E437AB2B435E71671412B64B423F06F33A5F490D4070C1DD36695D2FE69DE02FCC02C4BD16B9771CED2A27664B0D96E5FFE47C70C136D8BD1CBD7147D62BFCF69B6CDE462D41197243C8F8DF5F2071547D328C156C6B7318D2CBE3F20BA23AE39F98C499BDD497F66BD27826C2F13C50E7042611FFB19B5BE466A70FE7EE71248FAECD08638ACF400EA36AA0E33B77F7270F44AEEA3533793DB625BED07D4013A48950E3D5D4AD20A14BE3CF1CA4294E2F988EB44553C25ABFFBDD93A212F5D1E3095AFA0953192841280E3E4880A9F8EFBAA105991965DF65AF975E1A5D1F6845A23B6556CBEDF36B85C7C7244648EE04190C6B4A87DD806AB7EDA09D5CD440FA22D4D1B37F2B40EE1BCC996A3B8322866E99B8AE420F7870AB635CC7CF35CB81BFB7B390C79451DFF9EB60AD9DC8FF936FCBEBB4B0E957A5B01B98EA8C52CAE0981E555C53DA6EDA64066D86D9D4F6C781F7627BDCE25D937D40E56CABB38927F05EB4D694374C2EACD3B4385065BD6BF3EB3B3E48E9D7BF153B85E5ADD843D742ED682423EB7B601CDEFF124D88FDE6929A0A8E664E79CF43873A08E9C48C9F93B0DF13AA9AE3F16DD9B80F393EAD830D6BD45FBF54EE9124618866C2525ACFC04014ED30EE8439558D2BD5C780A89F4C6B942AED739B65A71D32AFBBD79A75BEA6D150FA500E7C017DD59F66D108AB773F40052E4134C57DEF9678D5C03D6795F1DD2445E4C78F9D44497B3E3C7216117CF4DBCAAD6D4E88A37715B0E54AAD807B1C464712872F3C70C367FC3D4CF1C5EA6399A3358C7F702243302DC13D85AF184E673DD98CCA11F63664D474ED06B167F8DFB5F1D78BD3A2B21FB782657D336BDD7754664FD3DAA97787CE36BB414C92B8282A3471E81DE2CC6233BB05B6C5DCCA95D777F76C7DA0CEE00DC12AB2A04B52530F2F968559F27AFC31054881A42375A88E2501507274265C51D29B40D115A1BBDF21A5479965A61B37883EBC96122F1B81DE5D2C081488738B90A4F452AAAE1DB786FDB73CBA47B5597B7AAF3829B91366A2
    <----- Request End -----*/

    private SibsResultCodeType validateSibsResult(String resultCode) {
        if (resultCode == null || resultCode.isEmpty()) {
            throw new NullPointerException("Problem fletching SIBS Result Code.");
        }
        for (SibsResultCodeType sibsResult : SibsResultCodeType.values()) {
            if (Pattern.compile(sibsResult.getRegex()).matcher(resultCode).find()) {
                return sibsResult;
            }
        }
        return null;
    }

    private String operationResultDescription(String resultDescription, SibsResultCodeType operationResultType) {
        if (operationResultType != null || resultDescription != null) {
            if (operationResultType != null && operationResultType.isSuccess()) {
                //TODO Verify if result is as expected if true
                return "Valid Operation / " + operationResultType.name() + " / " + resultDescription;
            } else {
                return "Invalid Operation / " + operationResultType.name() + " / " + resultDescription;
            }
        }
        return null;
    }

    private <T> T customMapper(String jsonPayload, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T result = mapper.readValue(jsonPayload, clazz);
        return result;
    }

    //TODO remove file logging on service after testing webhooks (needs to be on client, not on server side)
    private Logger fileLogger(String fileName) throws SecurityException, IOException {
        java.util.logging.Logger filelogger =
                java.util.logging.Logger.getLogger(SIBSOnlinePaymentsGatewayService.class.getName());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss.SSS");
        File logDir = new File("./logs/");
        if (!(logDir.exists()))
            logDir.mkdir();
        FileHandler fh = new FileHandler("logs/" + fileName + "_" + format.format(Calendar.getInstance().getTime()) + ".log");
        fh.setFormatter(new SimpleFormatter());
        filelogger.addHandler(fh);
        filelogger.setLevel(Level.FINEST);
        return logger;
    }

    public void closeClient() {
        client.close();
    }

}