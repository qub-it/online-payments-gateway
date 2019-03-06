package org.fenixedu.onlinepaymentsgateway.api;

public class OnlinePaymentServiceFactory {

    public static final SIBSOnlinePaymentsGatewayService createSIBSOnlinePaymentGatewayService(
            SIBSInitializeServiceBean initializeServiceBean) {
        return new SIBSOnlinePaymentsGatewayService(initializeServiceBean);
    }

}
