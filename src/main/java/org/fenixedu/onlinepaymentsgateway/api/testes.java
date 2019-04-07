package org.fenixedu.onlinepaymentsgateway.api;

import java.math.BigDecimal;

import org.fenixedu.onlinepaymentsgateway.sdk.NotificationBean;
import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class testes {

    public static void main(String[] args) throws Exception {
        System.out.println(generateMultibancoPayment().toString());
    }

    private static MbCheckoutResultBean generateMultibancoPayment() throws Exception {
        SIBSInitializeServiceBean initializeServiceBean = new SIBSInitializeServiceBean("8a82941765aec5280165c86e8ba944f2",
                "OGE4Mjk0MTg1YjY3NDU1NTAxNWI3YzE5MjhlODE3MzZ8UnI0N2VRZXNkVw==", "https://test.oppwa.com/v1", "25002", "EUR");
        SIBSOnlinePaymentsGatewayService applicationClient =
                OnlinePaymentServiceFactory.createSIBSOnlinePaymentGatewayService(initializeServiceBean);
        MbPrepareCheckoutInputBean mbPrepareCheckoutInputBean =
                new MbPrepareCheckoutInputBean(new BigDecimal("2.1"), "fcul47167", new DateTime(), new DateTime().plusDays(7));
        CustomerDataInputBean customerInputBean = new CustomerDataInputBean("1.1.1.1", "Pistolas", "Jonas", "Portugal");
        //MbCheckoutResultBean paymentResult = applicationClient.mbPrepareCheckout(mbPrepareCheckoutInputBean, null);
        MbCheckoutResultBean paymentResult = applicationClient.mbPrepareCheckout(mbPrepareCheckoutInputBean, customerInputBean);
        //return paymentResult.toString(); //MBResult {id=8ac7a4a2695dd81101695e6902904107, paymentType=PA, paymentBrand=SIBS_MULTIBANCO, amount=2.10, currency=EUR, descriptor=null, merchantTransactionId=fcul47167, result={code=000.100.110, description=Request successfully processed in 'Merchant in Integrator Test Mode'}, resultDetails=null, customer=null, billing=null, customParameters={SIBSMULTIBANCO_PtmntEntty=25002, SIBSMULTIBANCO_RefIntlDtTm=2019-03-08T17:45:29.292Z, SIBSMULTIBANCO_RefLmtDtTm=2019-03-15T17:45:29.292Z}, buildNumber=a73ed4ca34cf70ac2eeeab8eed20cadca78a5122@2019-03-07 04:42:24 +0000, timestamp=2019-03-08 17:45:34+0000, ndc=8a8294186316c36b016325a4e823400f_196289667f414dd8b9c3d591d5b90db6}
        return paymentResult;
    }

    @SuppressWarnings("unused") //getMBtransactionReport
    private MbCheckoutResultBean getTransactionReport(MbCheckoutResultBean mbResultBean) throws Exception {
        SIBSInitializeServiceBean initializeServiceBean = new SIBSInitializeServiceBean("8a82941765aec5280165c86e8ba944f2",
                "OGE4Mjk0MTg1YjY3NDU1NTAxNWI3YzE5MjhlODE3MzZ8UnI0N2VRZXNkVw==", "https://test.oppwa.com/v1", "25002", "EUR");
        SIBSOnlinePaymentsGatewayService applicationClient =
                OnlinePaymentServiceFactory.createSIBSOnlinePaymentGatewayService(initializeServiceBean);
        String transactionId = applicationClient.getMBPaymentTransactionId(mbResultBean);
        //assert beans iguais
        MbCheckoutResultBean transactionReportBean = applicationClient.getMBPaymentTransactionReport(transactionId);
        return transactionReportBean;
    }

    @SuppressWarnings("unused")
    private NotificationBean handleWebhookNotification() throws Exception { //servlet como arg
        //TODO outro construtor com o servlet
        //Receber o httpservlet request (argumento do metodo) - criar um bean com os dados do header e do payload, listener vs callback
        //handlenotification(servletrequest.getheader(Vetores)...
        //Interface com handlers do lado aplicacional (handlepaymentMb ou handleError()

        //Estrutura de dados faz + sentido para notificações que sao apenas para MB, se houver vários tipos deve-se fazer handle das varias opçoes

        SIBSInitializeServiceBean initializeServiceBean = new SIBSInitializeServiceBean("8a82941765aec5280165c86e8ba944f2",
                "OGE4Mjk0MTg1YjY3NDU1NTAxNWI3YzE5MjhlODE3MzZ8UnI0N2VRZXNkVw==",
                "E78D1277059759CD76EC4D387907DA6A4E6F882C52E10A78CF3587D8679FE00C");
        SIBSOnlinePaymentsGatewayService applicationClient =
                OnlinePaymentServiceFactory.createSIBSOnlinePaymentGatewayService(initializeServiceBean);
        String initializationVector = "33FF841C5FFCB95D3BA9593A";
        String authTag = "3DBF9FC2DCA65F737AF410A4A95DD610";
        String payload =
                "827906EE330B988F4EE254D748FD07D2FFC5116D6EDF0B7277A45033A28EAE1FC04DBF1A08B014BAB243FFC7CD0A313AECC9CE9B9A146F6E59DCF104867D78428E66031D516806B8FD84E325A6257F9B38F7F09C56ECB9095CC194091A589AE66D95291EB6136029393E1F7B4C0AB67F0F0628AFB90B7C10D296013A5EDF2658897575090F291521235738AE6742B744AED22541F3EF490031A7BD873436BFA83DBB8979744437D957F61032F592C06A03EB075CEDB1B7785205C7DD69F792B6EBE12401E75CE6A6DE7C113DDDC8CB6731F47025512AFD5B0787CDB063B50E5A2352A9CC49906AA360C44D4AF0A9AC86C7296AA91682EA4E255C104A68914DA00881E1B4B5DDD1E1A7A78EF8F044105C9377919978F0EE0DC670877EEDF884C0155C07FB9089FDC0A5DBB960655D9FA58EFE606BA1D29D09FD72CFBE6CFD8431D0D308798E22F93E6BA0C134DFF79E1B2C3F4CC32A7E141DCC191EBF5EF8A58EF580D9270EFD8155FF528CB2EE2AF057A289BFB9BC9CEDF2B75A7DADE2E9DD5D16CEBA0BFEC002D0BF947C4C9C4F0E11D065E9004B1164BF7412A39936BECE1268F2F200C9C0A48407DD2B15810681DB707E143B530F42B37E26D396D24DF1BD0DFD7FC4F82A9B9C2126BC6DAD3FAF7F97E037A0466478A0CE3BB324563A2882F250B820E437AB2B435E71671412B64B423F06F33A5F490D4070C1DD36695D2FE69DE02FCC02C4BD16B9771CED2A27664B0D96E5FFE47C70C136D8BD1CBD7147D62BFCF69B6CDE462D41197243C8F8DF5F2071547D328C156C6B7318D2CBE3F20BA23AE39F98C499BDD497F66BD27826C2F13C50E7042611FFB19B5BE466A70FE7EE71248FAECD08638ACF400EA36AA0E33B77F7270F44AEEA3533793DB625BED07D4013A48950E3D5D4AD20A14BE3CF1CA4294E2F988EB44553C25ABFFBDD93A212F5D1E3095AFA0953192841280E3E4880A9F8EFBAA105991965DF65AF975E1A5D1F6845A23B6556CBEDF36B85C7C7244648EE04190C6B4A87DD806AB7EDA09D5CD440FA22D4D1B37F2B40EE1BCC996A3B8322866E99B8AE420F7870AB635CC7CF35CB81BFB7B390C79451DFF9EB60AD9DC8FF936FCBEBB4B0E957A5B01B98EA8C52CAE0981E555C53DA6EDA64066D86D9D4F6C781F7627BDCE25D937D40E56CABB38927F05EB4D694374C2EACD3B4385065BD6BF3EB3B3E48E9D7BF153B85E5ADD843D742ED682423EB7B601CDEFF124D88FDE6929A0A8E664E79CF43873A08E9C48C9F93B0DF13AA9AE3F16DD9B80F393EAD830D6BD45FBF54EE9124618866C2525ACFC04014ED30EE8439558D2BD5C780A89F4C6B942AED739B65A71D32AFBBD79A75BEA6D150FA500E7C017DD59F66D108AB773F40052E4134C57DEF9678D5C03D6795F1DD2445E4C78F9D44497B3E3C7216117CF4DBCAAD6D4E88A37715B0E54AAD807B1C464712872F3C70C367FC3D4CF1C5EA6399A3358C7F702243302DC13D85AF184E673DD98CCA11F63664D474ED06B167F8DFB5F1D78BD3A2B21FB782657D336BDD7754664FD3DAA97787CE36BB414C92B8282A3471E81DE2CC6233BB05B6C5DCCA95D777F76C7DA0CEE00DC12AB2A04B52530F2F968559F27AFC31054881A42375A88E2501507274265C51D29B40D115A1BBDF21A5479965A61B37883EBC96122F1B81DE5D2C081488738B90A4F452AAAE1DB786FDB73CBA47B5597B7AAF3829B91366A2";
        //Bean devolvido com estados de pagamento
        return applicationClient.decodePaymentStateData(initializationVector, authTag, payload); //devolver bean em vez de json
    }

    @SuppressWarnings("unused") //TODO compare transactionbean with notificationbean
    private MbCheckoutResultBean getTransactionReportFromNotification(NotificationBean notificationBean) throws Exception {
        SIBSInitializeServiceBean initializeServiceBean = new SIBSInitializeServiceBean("8a82941765aec5280165c86e8ba944f2",
                "OGE4Mjk0MTg1YjY3NDU1NTAxNWI3YzE5MjhlODE3MzZ8UnI0N2VRZXNkVw==", "https://test.oppwa.com/v1", "25002", "EUR");
        SIBSOnlinePaymentsGatewayService applicationClient =
                OnlinePaymentServiceFactory.createSIBSOnlinePaymentGatewayService(initializeServiceBean);
        String transactionId = applicationClient.getNotificationMerchantTransactionId(notificationBean);
        //assert beans iguais
        MbCheckoutResultBean transactionReportBean = applicationClient.getMBPaymentTransactionReport(transactionId);
        return transactionReportBean;
    }

    @SuppressWarnings("unused")
    private String getNotificationSummary(NotificationBean notificationBean) throws Exception {
        SIBSInitializeServiceBean initializeServiceBean = new SIBSInitializeServiceBean("8a82941765aec5280165c86e8ba944f2",
                "OGE4Mjk0MTg1YjY3NDU1NTAxNWI3YzE5MjhlODE3MzZ8UnI0N2VRZXNkVw==", "https://test.oppwa.com/v1", "25002", "EUR");
        SIBSOnlinePaymentsGatewayService applicationClient =
                OnlinePaymentServiceFactory.createSIBSOnlinePaymentGatewayService(initializeServiceBean);
        String sibsTransactionId = applicationClient.getNotificationSIBSTransactionId(notificationBean);
        String merchantTransactionId = applicationClient.getNotificationMerchantTransactionId(notificationBean);
        String notificationType = applicationClient.getNotificationType(notificationBean);
        String notificationDescription = applicationClient.getNotificationResultDescription(notificationBean);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();
        ((ObjectNode) rootNode).put("NotificationType", notificationType);
        ((ObjectNode) rootNode).put("NotificationDescription", notificationDescription);
        ((ObjectNode) rootNode).put("NotificationSIBSTransactionID", sibsTransactionId);
        ((ObjectNode) rootNode).put("NotificationCustomizedTransactionID", merchantTransactionId);
        String jsonNotificationSummary = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        return jsonNotificationSummary;
    }

}
