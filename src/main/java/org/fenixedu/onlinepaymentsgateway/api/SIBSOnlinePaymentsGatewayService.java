package org.fenixedu.onlinepaymentsgateway.api;

import java.math.RoundingMode;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.fenixedu.onlinepaymentsgateway.sdk.NotificationBean;
import org.fenixedu.onlinepaymentsgateway.sdk.PaymentBrand;
import org.fenixedu.onlinepaymentsgateway.sdk.PaymentType;
import org.fenixedu.onlinepaymentsgateway.sdk.PrepareCheckout;
import org.fenixedu.onlinepaymentsgateway.sdk.PrepareCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.sdk.PrepareMBCheckout;
import org.fenixedu.onlinepaymentsgateway.sdk.PrepareMBCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.sdk.SIBSPaymentStatusBean;
import org.fenixedu.onlinepaymentsgateway.util.Decryption;
import org.joda.time.DateTime;

public class SIBSOnlinePaymentsGatewayService {

    Client client = ClientBuilder.newClient();
    private final WebTarget webTargetBase;
    private SIBSInitializeServiceBean initializeServiceBean;

    public SIBSOnlinePaymentsGatewayService(SIBSInitializeServiceBean initializeServiceBean) {
        this.initializeServiceBean = initializeServiceBean;
        this.webTargetBase = client.target(initializeServiceBean.getEndpointUrl());
    }

    public CheckoutResultBean prepareCheckout(PrepareCheckoutInputBean prepareCheckoutInputBean) {
        //TODO complete and change to exceptions
        assert (this.initializeServiceBean.isAuthPropertiesValid());
        assert (this.initializeServiceBean.isPaymentPropertiesValid());
        assert (prepareCheckoutInputBean != null);
        assert (prepareCheckoutInputBean.isPropertiesValid());
        //em vez de assert lançar exceçoes
        //Validate input
        if (prepareCheckoutInputBean.getAmount().scale() > 2) {
            throw new IllegalArgumentException("Amount.scale() <= 2");
        }

        WebTarget postRequest = webTargetBase.path("checkouts");

        //Values not dependant on input
        String bearerToken = this.initializeServiceBean.getBearerToken();
        String entityId = this.initializeServiceBean.getEntityId();
        String paymentCurrency = this.initializeServiceBean.getPaymentCurrency();
        String paymentType = "DB";
        String shopperResultUrl = prepareCheckoutInputBean.getShopperResultUrl();

        String paymentAmount = prepareCheckoutInputBean.getAmount().setScale(2, RoundingMode.HALF_EVEN).toString();
        String merchantTransactionId = prepareCheckoutInputBean.getMerchantTransactionId();

        Boolean useCreditCard = prepareCheckoutInputBean.getUseCreditCard();
        Boolean useMBway = prepareCheckoutInputBean.getUseMBway();
        Boolean useMB = prepareCheckoutInputBean.getUseMB();
        String paymentBrands = "";

        PrepareCheckout prepCheckout =
                new PrepareCheckout(entityId, paymentAmount, paymentCurrency, paymentType, merchantTransactionId);
        prepCheckout.setShopperResultUrl(shopperResultUrl);

        Form form = new Form(prepCheckout.asMap());
        Builder builder =
                postRequest.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        PrepareCheckoutResult result =
                builder.header(HttpHeaders.AUTHORIZATION, bearerToken).post(Entity.form(form), PrepareCheckoutResult.class);

        /*PrepareCheckoutResult json = postRequest.request("application/x-www-form-urlencoded; charset=UTF-8")
                .accept(MediaType.APPLICATION_JSON).post(Entity.form(form), PrepareCheckoutResult.class);*/

        OnlinePaymentOperationResultType operationResultType = validateSibsResult((PrepareMBCheckoutResult) result);
        String operationResultDescription = operationResultDescription((PrepareMBCheckoutResult) result, operationResultType);

        String requestLog = prepCheckout.toString();
        String responseLog = result.toString();

        CheckoutResultBean checkoutResult = new CheckoutResultBean(result.getId(), merchantTransactionId, result.getTimestamp(),
                shopperResultUrl, operationResultType, operationResultDescription, result.getResult().getCode(),
                result.getResult().getDescription());
        checkoutResult.setRequestLog(requestLog);
        checkoutResult.setResponseLog(responseLog);

        if (useCreditCard) {
            paymentBrands += " " + PaymentBrand.VISA.name() + " " + PaymentBrand.MASTER.name() + " " + PaymentBrand.AMEX.name();
        }
        if (useMBway) {
            paymentBrands += " " + PaymentBrand.MBWAY.name();
        }
        if (useMB) {
            paymentBrands += " " + PaymentBrand.SIBS_MULTIBANCO.name();
        }

        checkoutResult.setPaymentBrands(paymentBrands);

        //TODO Validate result has the values that were requested, change to Exceptions?
        assert (prepCheckout.getMerchantTransactionId() == checkoutResult.getMerchantTransactionId());

        return checkoutResult;
    }

    public PaymentFormBean createPaymentForm(CheckoutResultBean checkoutResultBean) throws Exception {
        assert (checkoutResultBean != null);
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
    public PaymentStatusBean getPaymentStatus(String checkoutId) {
        //TODO complete and change to exceptions
        assert (this.initializeServiceBean.isAuthPropertiesValid());
        assert (this.initializeServiceBean.isPaymentPropertiesValid());
        //em vez de assert lançar exceçoes
        //Validate input

        WebTarget postRequest = webTargetBase.path("checkouts/" + checkoutId + "/payment");
        //Values not dependant on input
        String bearerToken = this.initializeServiceBean.getBearerToken();
        String entityId = this.initializeServiceBean.getEntityId();

        MultivaluedMap<String, String> queryMap = new MultivaluedHashMap<>();
        queryMap.add("entityId", entityId);
        Form form = new Form(queryMap);
        Builder builder =
                postRequest.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        SIBSPaymentStatusBean result =
                builder.header(HttpHeaders.AUTHORIZATION, bearerToken).post(Entity.form(form), SIBSPaymentStatusBean.class);

        //OnlinePaymentOperationResultType operationResultType = validateSibsResult(result);
        //String operationResultDescription = operationResultDescription(result, operationResultType);

        //String requestLog = mbPrepCheckout.toString();
        String responseLog = result.toString();

        PaymentStatusBean paymentStatusBean = new PaymentStatusBean(result.getId(), result.getMerchantTransactionId(),
                result.getTimestamp(), PaymentType.valueOf(result.getPaymentType()),
                PaymentBrand.valueOf(result.getPaymentBrand()), result.getAmount(), result.getCurrency(),
                result.getResult().getCode(), result.getResult().getDescription(), null, null);//operationResultType, operationResultDescription)
        //mbCheckoutResult.setRequestLog(requestLog);
        paymentStatusBean.setResponseLog(responseLog);

        //TODO Validate result has the values that were requested, change to Exceptions?
        /*assert (prepCheckout.getMerchantTransactionId() == mbCheckoutResult.getMerchantTransactionId());
        assert (mbPrepCheckout.getPrepareCheckout().getAmount() == mbCheckoutResult.getAmount());
        assert (mbPrepCheckout.getPrepareCheckout().getCurrency() == mbCheckoutResult.getCurrency());
        assert (mbPrepCheckout.getPaymentBrand() == mbCheckoutResult.getPaymentBrand());
        assert (mbPrepCheckout.getPrepareCheckout().getPaymentType() == mbCheckoutResult.getPaymentType());
        */
        return paymentStatusBean;
    }

    public MbCheckoutResultBean mbPrepareCheckout(MbPrepareCheckoutInputBean mbPrepareCheckoutInputBean,
            CustomerDataInputBean customerInputBean) throws Exception {

        //TODO complete and change to exceptions
        assert (this.initializeServiceBean.isPaymentPropertiesValid());
        assert (mbPrepareCheckoutInputBean != null);
        assert (mbPrepareCheckoutInputBean.isPropertiesValid());
        //em vez de assert lançar exceçoes
        //Validate input
        if (mbPrepareCheckoutInputBean.getAmount().scale() > 2) {
            throw new IllegalArgumentException("Amount.scale() <= 2");
        }

        WebTarget postRequest = webTargetBase.path("payments");

        //Values not dependant on input
        String bearerToken = this.initializeServiceBean.getBearerToken();
        String entityId = this.initializeServiceBean.getEntityId();
        String paymentCurrency = this.initializeServiceBean.getPaymentCurrency();
        String paymentEntity = this.initializeServiceBean.getPaymentEntity();
        String paymentType = "PA";
        String paymentBrand = "SIBS_MULTIBANCO";
        String billingCountry = "PT";
        String merchantTransactionId = mbPrepareCheckoutInputBean.getMerchantTransactionId();
        String paymentAmount = mbPrepareCheckoutInputBean.getAmount().setScale(2, RoundingMode.HALF_EVEN).toString();

        PrepareCheckout prepCheckout =
                new PrepareCheckout(entityId, paymentAmount, paymentCurrency, paymentType, merchantTransactionId);

        String sibsRefIntDate = mbPrepareCheckoutInputBean.getSibsRefIntDate().toString();
        String sibsRefLmtDate = mbPrepareCheckoutInputBean.getSibsRefLmtDate().toString();

        PrepareMBCheckout mbPrepCheckout = new PrepareMBCheckout(prepCheckout, paymentBrand, paymentEntity, sibsRefIntDate,
                sibsRefLmtDate, billingCountry, customerInputBean);

        Form form = new Form(mbPrepCheckout.asMap());
        Builder builder =
                postRequest.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        PrepareMBCheckoutResult result =
                builder.header(HttpHeaders.AUTHORIZATION, bearerToken).post(Entity.form(form), PrepareMBCheckoutResult.class);

        /*.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        PrepareMBCheckoutResult result = builder.post(Entity.form(form), PrepareMBCheckoutResult.class);*/

        OnlinePaymentOperationResultType operationResultType = validateSibsResult(result);
        String operationResultDescription = operationResultDescription(result, operationResultType);

        String requestLog = mbPrepCheckout.toString();
        String responseLog = result.toString();

        MbCheckoutResultBean mbCheckoutResult = new MbCheckoutResultBean(result.getId(), result.getMerchantTransactionId(),
                result.getTimestamp(), result.getAmount(), result.getCurrency(), result.getPaymentBrand(),
                result.getPaymentType(), result.getCustomParameters().getSibsPaymentEntity(),
                result.getResultDetails().getPmtRef(), DateTime.parse(result.getCustomParameters().getSibsRefIntDate()),
                DateTime.parse(result.getCustomParameters().getSibsRefLmtDate()), operationResultType, operationResultDescription,
                result.getResult().getCode(), result.getResult().getDescription());
        mbCheckoutResult.setRequestLog(requestLog);
        mbCheckoutResult.setResponseLog(responseLog);

        //TODO Validate result has the values that were requested, change to Exceptions?
        assert (prepCheckout.getMerchantTransactionId() == mbCheckoutResult.getMerchantTransactionId());
        assert (mbPrepCheckout.getPrepareCheckout().getAmount() == mbCheckoutResult.getAmount());
        assert (mbPrepCheckout.getPrepareCheckout().getCurrency() == mbCheckoutResult.getCurrency());
        assert (mbPrepCheckout.getPaymentBrand() == mbCheckoutResult.getPaymentBrand());
        assert (mbPrepCheckout.getPrepareCheckout().getPaymentType() == mbCheckoutResult.getPaymentType());

        return mbCheckoutResult;
    }

    public String getMBPaymentTransactionId(MbCheckoutResultBean mbCheckoutResultBean) {
        return mbCheckoutResultBean.getMerchantTransactionId();
    }

    public MbCheckoutResultBean getMBPaymentTransactionReport(String merchantTransactionId) {
        //TODO change to MBTransactionReportBean - Payment é um array de payments, como resolver?
        WebTarget postRequest = webTargetBase.path("query");
        String bearerToken = this.initializeServiceBean.getBearerToken();
        String entityId = this.initializeServiceBean.getEntityId();

        MultivaluedMap<String, String> queryMap = new MultivaluedHashMap<>();
        queryMap.add("entityId", entityId);
        queryMap.add("merchantTransactionId", merchantTransactionId);

        Form form = new Form(queryMap);

        Builder builder =
                postRequest.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        PrepareMBCheckoutResult result =
                builder.header(HttpHeaders.AUTHORIZATION, bearerToken).post(Entity.form(form), PrepareMBCheckoutResult.class);

        MbCheckoutResultBean mbCheckoutResult = new MbCheckoutResultBean();
        mbCheckoutResult.setPaymentGatewayResultCode(result.getResult().getCode());
        mbCheckoutResult.setPaymentGatewayResultDescription(result.getResult().getDescription());

        return mbCheckoutResult;
    }

    public NotificationBean decodePaymentStateData(String initializationVector, String authTag, String encryptedPayload)
            throws Exception {
        //Receber o httpservlet request (argumento do metodo) - criar um bean com os dados do header e do payload, listener vs callback
        //handlenotification(servletrequest.getheader(Vetores)...
        //Interface com handlers do lado aplicacional (handlepaymentMb ou handleError()

        //Estrutura de dados faz + sentido para notificações que sao apenas para MB, se houver vários tipos deve-se fazer handle das varias opçoes
        //handle das varias opções, notification bean geral + notificationbean especificos
        Decryption notification =
                new Decryption(this.initializeServiceBean.getAesKey(), initializationVector, authTag, encryptedPayload);
        //validate result with OnlinePaymentOperationResultType && validateSibsResult
        return notification.handleNotification(notification.decryptPayload());
    }

    public String getNotificationMerchantTransactionId(NotificationBean notificationBean) {
        return notificationBean.getMerchantTransactionId();
    }

    public String getNotificationSIBSTransactionId(NotificationBean notificationBean) {
        return notificationBean.getId();
    }

    public String getNotificationType(NotificationBean notificationBean) {
        return notificationBean.getType();
    }

    public String getNotificationResultDescription(NotificationBean notificationBean) {
        //TODO Alterar para OnlinePaymentOperationResultType && validateSibsResult
        return notificationBean.getResult().getDescription();
    }

    private OnlinePaymentOperationResultType validateSibsResult(PrepareMBCheckoutResult result) {
        if (!"3454-345634.000".equals(result.getResult().getCode())) { //comparar com SibsResultCodes
            return OnlinePaymentOperationResultType.ERROR_ON_PAYMENT_GATEWAY;
        } //TODO Adicionar validações
        return null;
        //validar codigo de retorn da sibs e criar um OnlinePaymentOperationResultType, validando todos os campos necessários.
    }

    private String operationResultDescription(PrepareMBCheckoutResult result,
            OnlinePaymentOperationResultType operationResultType) {
        if (operationResultType == OnlinePaymentOperationResultType.SUCCESS) {
            return "Operation Success";
        } else if (operationResultType == OnlinePaymentOperationResultType.ERROR_ON_PAYMENT_GATEWAY) {
            return "Error on payment gateway: " + result.getResult().getDescription();
        }//TODO Adicionar validações

        return null;
    }

    public void closeClient() {
        client.close();
    }

}