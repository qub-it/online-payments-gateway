package org.fenixedu.onlinepaymentsgateway.api;

import java.math.RoundingMode;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.fenixedu.onlinepaymentsgateway.sdk.Authentication;
import org.fenixedu.onlinepaymentsgateway.sdk.PrepareCheckout;
import org.fenixedu.onlinepaymentsgateway.sdk.PrepareCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.sdk.PrepareMBCheckout;
import org.fenixedu.onlinepaymentsgateway.sdk.PrepareMBCheckoutResult;
import org.fenixedu.onlinepaymentsgateway.util.Decryption;

public class SIBSOnlinePaymentsGatewayService {

    Client client = ClientBuilder.newClient();
    private final WebTarget webTargetBase;
    private SIBSInitializeServiceBean initializeServiceBean;

    public SIBSOnlinePaymentsGatewayService(SIBSInitializeServiceBean initializeServiceBean) {
        this.initializeServiceBean = initializeServiceBean;
        this.webTargetBase = client.target(initializeServiceBean.getEndpointUrl());
    }

    public String prepareCheckout(String userId, String password, String entityId, String amount, String currency,
            String paymentType) {
        WebTarget postit = webTargetBase.path("checkouts");
        Authentication auth = new Authentication(userId, password, entityId);
        PrepareCheckout prepCheckout = new PrepareCheckout(auth, amount, currency, paymentType);
        Form form = new Form(prepCheckout.asMap());
        PrepareCheckoutResult json = postit.request("application/x-www-form-urlencoded; charset=UTF-8")
                .accept(MediaType.APPLICATION_JSON).post(Entity.form(form), PrepareCheckoutResult.class);
        return json.toString();
    }

    public String mbPrepareCheckout(MbPrepareCheckoutInputBean mbPrepareCheckoutInputBean, CustomerInputBean customerInputBean)
            throws Exception {

        assert (mbPrepareCheckoutInputBean != null);
        assert (mbPrepareCheckoutInputBean.isPropertiesValid());

        //TODO asserts para outros arg necessarios

        WebTarget postit = webTargetBase.path("payments");

        Authentication auth = new Authentication(this.initializeServiceBean.getUserId(), this.initializeServiceBean.getPassword(),
                this.initializeServiceBean.getEntityId());

        String currency = this.initializeServiceBean.getCurrency();
        String mbEntity = this.initializeServiceBean.getMbEntity();
        String paymentType = "PA";
        String paymentBrand = "SIBS_MULTIBANCO";

        if (mbPrepareCheckoutInputBean.getAmount().scale() > 2) {
            throw new RuntimeException("Amount.scale() <= 2");
        }
        String amount = mbPrepareCheckoutInputBean.getAmount().setScale(2, RoundingMode.HALF_EVEN).toString();

        PrepareCheckout prepCheckout = new PrepareCheckout(auth, amount, currency, paymentType);

        String merchantTransactionId = mbPrepareCheckoutInputBean.getMerchantTransactionId();
        String sIBSMULTIBANCO_RefIntlDtTm = mbPrepareCheckoutInputBean.getsIBSMULTIBANCO_RefIntlDtTm().toString();
        String sIBSMULTIBANCO_RefLmtDtTm = mbPrepareCheckoutInputBean.getsIBSMULTIBANCO_RefLmtDtTm().toString();

        PrepareMBCheckout mbPrepCheckout = new PrepareMBCheckout(prepCheckout, paymentBrand, merchantTransactionId, mbEntity,
                sIBSMULTIBANCO_RefIntlDtTm, sIBSMULTIBANCO_RefLmtDtTm, customerInputBean);

        Form form = new Form(mbPrepCheckout.asMap());
        PrepareMBCheckoutResult json = postit.request("application/x-www-form-urlencoded; charset=UTF-8")
                .accept(MediaType.APPLICATION_JSON).post(Entity.form(form), PrepareMBCheckoutResult.class);
        return json.toString();
    }

    public String decodePaymentStateData(String initializationVector, String aesKey, String authTag, String encryptedPayload)
            throws Exception {
        Decryption notification = new Decryption(initializationVector, aesKey, authTag, encryptedPayload);
        return notification.decrypt();
    }

    public void closeClient() {
        client.close();
    }

}