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

import org.fenixedu.onlinepaymentsgateway.sdk.PrepareCheckout;
import org.fenixedu.onlinepaymentsgateway.sdk.PrepareCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.sdk.PrepareMBCheckout;
import org.fenixedu.onlinepaymentsgateway.sdk.PrepareMBCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.util.Decryption;
import org.joda.time.DateTime;

public class SIBSOnlinePaymentsGatewayService {

    Client client = ClientBuilder.newClient();
    private final WebTarget webTargetBase;
    private SIBSInitializeServiceBean initializeServiceBean;

    public SIBSOnlinePaymentsGatewayService(SIBSInitializeServiceBean initializeServiceBean) {
        this.initializeServiceBean = initializeServiceBean; //adicionar bearer token
        this.webTargetBase = client.target(initializeServiceBean.getEndpointUrl());
    }

    public String prepareCheckout(String entityId, String amount, String currency, String paymentType) {
        WebTarget postit = webTargetBase.path("checkouts");
        PrepareCheckout prepCheckout = new PrepareCheckout(entityId, amount, currency, paymentType);
        Form form = new Form(prepCheckout.asMap());
        PrepareCheckoutResult json = postit.request("application/x-www-form-urlencoded; charset=UTF-8")
                .accept(MediaType.APPLICATION_JSON).post(Entity.form(form), PrepareCheckoutResult.class);
        return json.toString();
    }

    public MbCheckoutResultBean mbPrepareCheckout(MbPrepareCheckoutInputBean mbPrepareCheckoutInputBean,
            CustomerInputBean customerInputBean) throws Exception {

        //TODO complete asserts
        assert (this.initializeServiceBean.isPaymentPropertiesValid());
        assert (mbPrepareCheckoutInputBean != null);
        assert (mbPrepareCheckoutInputBean.isPropertiesValid());
        //em vez de assert lançar exceçoes
        //Validate input
        if (mbPrepareCheckoutInputBean.getAmount().scale() > 2) {
            throw new RuntimeException("Amount.scale() <= 2");
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

        String paymentAmount = mbPrepareCheckoutInputBean.getAmount().setScale(2, RoundingMode.HALF_EVEN).toString();

        PrepareCheckout prepCheckout = new PrepareCheckout(entityId, paymentAmount, paymentCurrency, paymentType);

        String merchantTransactionId = mbPrepareCheckoutInputBean.getMerchantTransactionId();
        String sibsRefIntDate = mbPrepareCheckoutInputBean.getSibsRefIntDate().toString();
        String sibsRefLmtDate = mbPrepareCheckoutInputBean.getSibsRefLmtDate().toString();

        PrepareMBCheckout mbPrepCheckout = new PrepareMBCheckout(prepCheckout, paymentBrand, merchantTransactionId, paymentEntity,
                sibsRefIntDate, sibsRefLmtDate, billingCountry, customerInputBean);

        Form form = new Form(mbPrepCheckout.asMap());
        Builder builder =
                postRequest.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        PrepareMBCheckoutResult result =
                builder.header(HttpHeaders.AUTHORIZATION, bearerToken).post(Entity.form(form), PrepareMBCheckoutResult.class);

        /*.request("application/x-www-form-urlencoded; charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        PrepareMBCheckoutResult result = builder.post(Entity.form(form), PrepareMBCheckoutResult.class);*/

        //TODO change header

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

        //Validate result has the values that were requested
        assert (mbPrepCheckout.getMerchantTransactionId() == mbCheckoutResult.getMerchantTransactionId());
        assert (mbPrepCheckout.getPrepareCheckout().getAmount() == mbCheckoutResult.getAmount());
        assert (mbPrepCheckout.getPrepareCheckout().getCurrency() == mbCheckoutResult.getCurrency());
        assert (mbPrepCheckout.getPaymentBrand() == mbCheckoutResult.getPaymentBrand());
        assert (mbPrepCheckout.getPrepareCheckout().getPaymentType() == mbCheckoutResult.getPaymentType());

        return mbCheckoutResult;
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

    private OnlinePaymentOperationResultType validateSibsResult(PrepareMBCheckoutResult result) {
        if (!"3454-345634.000".equals(result.getResult().getCode())) { //comparar com SibsResultCodes
            return OnlinePaymentOperationResultType.ERROR_ON_PAYMENT_GATEWAY;
        } //TODO Adicionar validações
        return null;

        //validar codigo de retorn da sibs e criar um OnlinePaymentOperationResultType, validando todos os campos necessários.
    }

    //Receber o httpservlet request (argumento do metodo) - criar um bean com os dados do header e do payload, listener vs callback
    //handlenotification(servletrequest.getheader(Vetores)...
    //Interface com handlers do lado aplicacional (handlepaymentMb ou handleError()

    //Estrutura de dados faz + sentido para notificações que sao apenas para MB, se houver vários tipos deve-se fazer handle das varias opçoes

    public String decodePaymentStateData(String initializationVector, String aesKey, String authTag, String encryptedPayload)
            throws Exception {
        Decryption notification = new Decryption(initializationVector, aesKey, authTag, encryptedPayload);
        return notification.decrypt();
    }

    public void closeClient() {
        client.close();
    }

}