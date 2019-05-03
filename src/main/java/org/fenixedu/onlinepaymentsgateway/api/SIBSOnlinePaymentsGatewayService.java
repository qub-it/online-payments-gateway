package org.fenixedu.onlinepaymentsgateway.api;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
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
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.NotificationBean;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PaymentBrand;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareCheckout;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareMBCheckout;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareMBCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareMBWayCheckout;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.PrepareMBWayCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.SIBSPaymentStatusBean;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.SibsResultCodeType;
import org.fenixedu.onlinepaymentsgateway.sibs.sdk.TransactionReportBean;
import org.fenixedu.onlinepaymentsgateway.util.Decryption;
import org.glassfish.jersey.logging.LoggingFeature;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SIBSOnlinePaymentsGatewayService {

    private Logger logger = Logger.getLogger(getClass().getName());
    private Feature feature = new LoggingFeature(logger, Level.INFO, null, null);
    private Client client = ClientBuilder.newBuilder().register(feature).build();
    private WebTarget webTargetBase;
    private SIBSInitializeServiceBean initializeServiceBean;

    public SIBSOnlinePaymentsGatewayService(SIBSInitializeServiceBean initializeServiceBean) {
        this.initializeServiceBean = initializeServiceBean;
        if (initializeServiceBean.getEndpointUrl() != null) {
            this.webTargetBase = client.target(initializeServiceBean.getEndpointUrl());
        }
    }

    public MbCheckoutResultBean mbPrepareCheckout(MbPrepareCheckoutInputBean mbPrepareCheckoutInputBean,
            CustomerDataInputBean customerInputBean) throws OnlinePaymentsGatewayCommunicationException {
        if (!this.initializeServiceBean.isAuthPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service Authentication");
        }
        if (!this.initializeServiceBean.isPaymentPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service payment settings");
        }
        if (mbPrepareCheckoutInputBean == null || !mbPrepareCheckoutInputBean.isPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Payment input");
        }
        if (mbPrepareCheckoutInputBean.getAmount().scale() > 2) {
            throw new IllegalArgumentException("Invalid Amount, Amount.scale() <= 2");
        }

        WebTarget target = webTargetBase.path("payments");

        final String bearerToken = this.initializeServiceBean.getBearerToken();
        final String entityId = this.initializeServiceBean.getEntityId();
        final String paymentCurrency = this.initializeServiceBean.getPaymentCurrency();
        final String paymentEntity = this.initializeServiceBean.getPaymentEntity();
        final String paymentType = "PA";
        final String paymentBrand = "SIBS_MULTIBANCO";
        final String billingCountry = "PT";
        final String paymentAmount = mbPrepareCheckoutInputBean.getAmount().setScale(2, RoundingMode.HALF_EVEN).toString();

        final String sibsRefIntDate = mbPrepareCheckoutInputBean.getSibsRefIntDate().toString();
        final String sibsRefLmtDate = mbPrepareCheckoutInputBean.getSibsRefLmtDate().toString();

        final PrepareMBCheckout mbPrepCheckout = new PrepareMBCheckout(entityId, paymentAmount, paymentCurrency, paymentType,
                paymentBrand, paymentEntity, sibsRefIntDate, sibsRefLmtDate, billingCountry, customerInputBean);

        if (mbPrepareCheckoutInputBean.getMerchantTransactionId() != null) {
            String merchantTransactionId = mbPrepareCheckoutInputBean.getMerchantTransactionId();
            mbPrepCheckout.setMerchantTransactionId(merchantTransactionId);
        }

        //TODO trycatch geral, com catch para o Bean, contem jsonPayload, Stacktrace
        final String requestLog = mbPrepCheckout.toString();
        Form form = new Form(mbPrepCheckout.asMap());

        Builder builder = target.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);

        //try { how to handle because can't have responseLog - how to handle httpbadrequest...
        final String responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).post(Entity.form(form), String.class);
        try {
            PrepareMBCheckoutResult result = customMapper(responseLog, PrepareMBCheckoutResult.class);
            SibsResultCodeType operationResultType = validateSibsResult(result.getResult().getCode());
            String operationResultDescription =
                    operationResultDescription(result.getResult().getDescription(), operationResultType);

            //components that might not exist need an IF
            MbCheckoutResultBean mbCheckoutResult = new MbCheckoutResultBean(result.getId(), result.getMerchantTransactionId(),
                    result.getTimestamp(), result.getAmount(), result.getCurrency(), result.getPaymentBrand(),
                    result.getPaymentType(), result.getCustomParameters().getSibsPaymentEntity(),
                    result.getResultDetails().getPmtRef(), result.getCustomParameters().getSibsRefIntDate(),
                    result.getCustomParameters().getSibsRefLmtDate(), operationResultType, operationResultDescription,
                    result.getResult().getCode(), result.getResult().getDescription());

            mbCheckoutResult.setRequestLog(requestLog);
            mbCheckoutResult.setResponseLog(responseLog);

            boolean validAnswer = true;
            validAnswer &= mbPrepCheckout.getAmount() != null && mbCheckoutResult.getAmount() != null
                    && mbPrepCheckout.getAmount().equals(mbCheckoutResult.getAmount());
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

            return mbCheckoutResult;

        } catch (IOException e) {
            e.printStackTrace();
            MbCheckoutResultBean mbCheckoutResult = new MbCheckoutResultBean();
            mbCheckoutResult.setRequestLog(requestLog);
            mbCheckoutResult.setResponseLog(responseLog);
            mbCheckoutResult.setException(e);
            return mbCheckoutResult;
        }
        /*} catch (BadRequestException e) {
            //how to handle because can't have responseLog - String.valueOf(result.getResult().getParameterErrors() != null) / paymentStatusBean.getResult().getParameterErrors().get(0).getMessage()
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, null, e);
        } catch (WebApplicationException e) {
            e.printStackTrace();
            //throw new OnlinePaymentsGatewayCommunicationException(requestLog, null, e);
        }*/

    }

    public MbWayCheckoutResultBean mbwayPrepareCheckout(MbWayPrepareCheckoutInputBean mbwayPrepareCheckoutInputBean,
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
        final String paymentType = "DB";
        final String paymentBrand = "MBWAY";
        final String paymentAmount = mbwayPrepareCheckoutInputBean.getAmount().setScale(2, RoundingMode.HALF_EVEN).toString();
        final String phoneNumber = mbwayPrepareCheckoutInputBean.getPhoneNumber();

        final PrepareMBWayCheckout mbwayPrepCheckout = new PrepareMBWayCheckout(entityId, paymentAmount, paymentCurrency,
                paymentType, paymentBrand, phoneNumber, customerInputBean);

        if (mbwayPrepareCheckoutInputBean.getMerchantTransactionId() != null) {
            String merchantTransactionId = mbwayPrepareCheckoutInputBean.getMerchantTransactionId();
            mbwayPrepCheckout.setMerchantTransactionId(merchantTransactionId);
        }

        //TODO trycatch geral, com catch para o Bean, contem jsonPayload, Stacktrace
        final String requestLog = mbwayPrepCheckout.toString();
        Form form = new Form(mbwayPrepCheckout.asMap());

        Builder builder = target.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);

        //try { how to handle because can't have responseLog - how to handle httpbadrequest...
        final String responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).post(Entity.form(form), String.class);
        try {
            PrepareMBWayCheckoutResult result = customMapper(responseLog, PrepareMBWayCheckoutResult.class);
            String answerContainingPhoneNumber = result.getResultDetails().getConnectorTxID3();
            String phoneNumberResult = answerContainingPhoneNumber.substring(answerContainingPhoneNumber.lastIndexOf("||") + 2,
                    answerContainingPhoneNumber.lastIndexOf("||") + 15);
            SibsResultCodeType operationResultType = validateSibsResult(result.getResult().getCode());
            String operationResultDescription =
                    operationResultDescription(result.getResult().getDescription(), operationResultType);

            //TODO components that might not exist need an IF
            MbWayCheckoutResultBean mbwayCheckoutResult =
                    new MbWayCheckoutResultBean(result.getId(), result.getMerchantTransactionId(), result.getTimestamp(),
                            result.getAmount(), result.getCurrency(), result.getPaymentBrand(), result.getPaymentType(),
                            phoneNumberResult, result.getResultDetails().getAcquirerResponse(), operationResultType,
                            operationResultDescription, result.getResult().getCode(), result.getResult().getDescription());

            mbwayCheckoutResult.setRequestLog(requestLog);
            mbwayCheckoutResult.setResponseLog(responseLog);

            boolean validAnswer = true;
            validAnswer &= mbwayPrepCheckout.getAmount() != null && mbwayCheckoutResult.getAmount() != null
                    && mbwayPrepCheckout.getAmount().equals(mbwayCheckoutResult.getAmount());
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

        } catch (IOException e) {
            e.printStackTrace();
            MbWayCheckoutResultBean mbwayCheckoutResult = new MbWayCheckoutResultBean();
            mbwayCheckoutResult.setRequestLog(requestLog);
            mbwayCheckoutResult.setResponseLog(responseLog);
            mbwayCheckoutResult.setException(e);
            return mbwayCheckoutResult;
        }
        /*} catch (BadRequestException e) {
            //how to handle because can't have responseLog - String.valueOf(result.getResult().getParameterErrors() != null) / paymentStatusBean.getResult().getParameterErrors().get(0).getMessage()
            throw new OnlinePaymentsGatewayCommunicationException(requestLog, null, e);
        } catch (WebApplicationException e) {
            e.printStackTrace();
            //throw new OnlinePaymentsGatewayCommunicationException(requestLog, null, e);
        }*/

    }

    public TransactionReportBean getPaymentTransactionReport(String transactionId) {
        if (!this.initializeServiceBean.isAuthPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service Authentication");
        }
        if (transactionId == null || transactionId.isEmpty()) {
            throw new IllegalArgumentException("Invalid Transaction Id");
        }

        WebTarget target = webTargetBase.path("query/" + transactionId);

        final String bearerToken = this.initializeServiceBean.getBearerToken();
        final String entityId = this.initializeServiceBean.getEntityId();

        Builder builder = target.queryParam("entityId", entityId).request("application/x-www-form-urlencoded; charset=UTF-8")
                .accept(MediaType.APPLICATION_JSON);

        //TODO trycatch geral, com catch para o Bean, contem jsonPayload, Stacktrace
        final String requestLog =
                "Entity Id = " + entityId + ", Authorization = " + bearerToken + ", Transaction Id = " + transactionId;
        final String responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).get(String.class);

        try {
            TransactionReportBean transactionReport = customMapper(responseLog, TransactionReportBean.class);
            SibsResultCodeType operationResultType = validateSibsResult(transactionReport.getResult().getCode());
            String operationResultDescription =
                    operationResultDescription(transactionReport.getResult().getDescription(), operationResultType);
            transactionReport.setOperationResultType(operationResultType);
            transactionReport.setOperationResultDescription(operationResultDescription);
            transactionReport.setRequestLog(requestLog);
            transactionReport.setResponseLog(responseLog);
            return transactionReport;
        } catch (IOException e) {
            e.printStackTrace();
            TransactionReportBean transactionReport = new TransactionReportBean();
            transactionReport.setRequestLog(requestLog);
            transactionReport.setResponseLog(responseLog);
            transactionReport.setException(e);
            return transactionReport;
        }
    }

    //For CC use: 4188 5300 0999 2951     08/19       092
    public CheckoutResultBean prepareCheckout(PrepareCheckoutInputBean prepareCheckoutInputBean) {
        if (!this.initializeServiceBean.isAuthPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service Authentication");
        }
        if (!this.initializeServiceBean.isPaymentPropertiesValid()) {
            throw new IllegalArgumentException("Invalid Service payment settings");
        }
        if (prepareCheckoutInputBean == null || !prepareCheckoutInputBean.isPropertiesValid()) {
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
        String paymentType = "";
        if (useMB) {
            paymentType = "PA";
        } else {
            paymentType = "DB";
        }

        final String billingCountry = "PT";
        final String paymentAmount = prepareCheckoutInputBean.getAmount().setScale(2, RoundingMode.HALF_EVEN).toString();

        final String sibsRefIntDate = prepareCheckoutInputBean.getSibsRefIntDate().toString();
        final String sibsRefLmtDate = prepareCheckoutInputBean.getSibsRefLmtDate().toString();

        final String shopperResultUrl = prepareCheckoutInputBean.getShopperResultUrl();

        String paymentBrands = "";

        PrepareCheckout prepCheckout = new PrepareCheckout(entityId, paymentAmount, paymentCurrency, paymentType, paymentEntity,
                sibsRefIntDate, sibsRefLmtDate, billingCountry);
        if (prepareCheckoutInputBean.getMerchantTransactionId() != null) {
            final String merchantTransactionId = prepareCheckoutInputBean.getMerchantTransactionId();
            prepCheckout.setMerchantTransactionId(merchantTransactionId);
        }

        final String requestLog = prepCheckout.toString();
        Form form = new Form(prepCheckout.asMap());

        Builder builder = target.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);

        //try { how to handle because can't have responseLog - how to handle httpbadrequest...
        final String responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).post(Entity.form(form), String.class);

        try {
            PrepareCheckoutResult result = customMapper(responseLog, PrepareCheckoutResult.class);
            SibsResultCodeType operationResultType = validateSibsResult(result.getResult().getCode());
            String operationResultDescription =
                    operationResultDescription(result.getResult().getDescription(), operationResultType);

            CheckoutResultBean checkoutResult = new CheckoutResultBean(result.getId(), result.getTimestamp(), shopperResultUrl,
                    paymentAmount, paymentCurrency, operationResultType, operationResultDescription, result.getResult().getCode(),
                    result.getResult().getDescription());

            checkoutResult.setRequestLog(requestLog);
            checkoutResult.setResponseLog(responseLog);

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

        } catch (IOException e) {
            e.printStackTrace();
            CheckoutResultBean checkoutResult = new CheckoutResultBean();
            checkoutResult.setRequestLog(requestLog);
            checkoutResult.setResponseLog(responseLog);
            checkoutResult.setException(e);
            return checkoutResult;
        }

    }

    public PaymentFormBean createPaymentForm(CheckoutResultBean checkoutResultBean) throws Exception {
        //assert (checkoutResultBean != null);
        String endpointUrl = initializeServiceBean.getEndpointUrl();
        String checkoutId = checkoutResultBean.getId();
        String shopperResultUrl = checkoutResultBean.getShopperResultUrl();
        String paymentBrands = checkoutResultBean.getPaymentBrands();

        String htmlScript = "<script src='" + endpointUrl + "/paymentWidgets.js?checkoutId=" + checkoutId + "'></script>";
        String htmlForm =
                "<form action='" + shopperResultUrl + "' class='paymentWidgets' data-brands='" + paymentBrands + "'></form>";

        PaymentFormBean paymentForm = new PaymentFormBean(htmlScript, htmlForm);

        return paymentForm;
    }

    //TODO deve ser utilizado como callback quando redirect ao shopperResultUrl
    public PaymentStatusBean getPaymentStatus(String checkoutId) throws OnlinePaymentsGatewayCommunicationException {
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

        //try { how to handle because can't have responseLog - how to handle httpbadrequest...
        final String requestLog =
                "Entity Id = " + entityId + ", Authorization = " + bearerToken + ", Checkout Id = " + checkoutId;
        final String responseLog = builder.header(HttpHeaders.AUTHORIZATION, bearerToken).get(String.class);

        try {
            SIBSPaymentStatusBean result = customMapper(responseLog, SIBSPaymentStatusBean.class);
            SibsResultCodeType operationResultType = validateSibsResult(result.getResult().getCode());
            String operationResultDescription =
                    operationResultDescription(result.getResult().getDescription(), operationResultType);

            //TODO Complete PaymentStatusBean options cartoes,mb,mbway
            PaymentStatusBean paymentStatusBean = new PaymentStatusBean(result.getId(), result.getMerchantTransactionId(),
                    result.getTimestamp(), result.getPaymentType(), result.getPaymentBrand(), result.getAmount(),
                    result.getCurrency(), result.getResult().getCode(), result.getResult().getDescription(), operationResultType,
                    operationResultDescription);

            paymentStatusBean.setRequestLog(requestLog);
            paymentStatusBean.setResponseLog(responseLog);

            return paymentStatusBean;
        } catch (IOException e) {
            e.printStackTrace();
            PaymentStatusBean paymentStatusBean = new PaymentStatusBean();
            paymentStatusBean.setRequestLog(requestLog);
            paymentStatusBean.setResponseLog(responseLog);
            paymentStatusBean.setException(e);
            return paymentStatusBean;
        }
    }

    //TODO estado de pagamento (isPaid(boolean) e outros estados para apps)
    public NotificationBean decodePaymentStateData(String initializationVector, String authTag, String encryptedPayload)
            throws Exception {
        //Receber o httpservlet request (argumento do metodo) - criar um bean com os dados do header e do payload, listener vs callback
        //handlenotification(servletrequest.getheader(Vetores)...
        //Interface com handlers do lado aplicacional (handlepaymentMb ou handleError()

        //Estrutura de dados faz + sentido para notificações que sao apenas para MB, se houver vários tipos deve-se fazer handle das varias opçoes
        //handle das varias opções, notification bean geral + notificationbean especificos
        //Incluir o tipo de resposta a dar de volta(codigo 200 ou 500)
        Decryption notification =
                new Decryption(this.initializeServiceBean.getAesKey(), initializationVector, authTag, encryptedPayload);
        //validate result with OnlinePaymentOperationResultType && validateSibsResult
        return notification.handleNotification(notification.decryptPayload());
    }

    public NotificationBean handleNotificationServletRequest(HttpServletRequest httpServletRequest) throws Exception {
        //Receber o httpservlet request (argumento do metodo) - criar um bean com os dados do header e do payload, listener vs callback
        //handlenotification(servletrequest.getheader(Vetores)...
        //Interface com handlers do lado aplicacional (handlepaymentMb ou handleError()

        //Estrutura de dados faz + sentido para notificações que sao apenas para MB, se houver vários tipos deve-se fazer handle das varias opçoes
        //handle das varias opções, notification bean geral + notificationbean especificos
        String initializationVector = httpServletRequest.getHeader("X-Initialization-Vector");
        String authTag = httpServletRequest.getHeader("X-Authentication-Tag");
        String encryptedPayload = httpServletRequest.getReader().readLine();

        Decryption notification =
                new Decryption(this.initializeServiceBean.getAesKey(), initializationVector, authTag, encryptedPayload);
        //validate result with OnlinePaymentOperationResultType && validateSibsResult
        return notification.handleNotification(notification.decryptPayload());
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

    public void closeClient() {
        client.close();
    }

}