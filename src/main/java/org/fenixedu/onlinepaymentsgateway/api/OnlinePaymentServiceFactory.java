package org.fenixedu.onlinepaymentsgateway.api;

public class OnlinePaymentServiceFactory {

    public static final SIBSOnlinePaymentsGatewayService createSIBSOnlinePaymentGatewayService(
            SIBSInitializeServiceBean initializeServiceBean) {
        assert (initializeServiceBean != null);
        assert (initializeServiceBean.isAuthPropertiesValid());
        return new SIBSOnlinePaymentsGatewayService(initializeServiceBean);
    }

}
