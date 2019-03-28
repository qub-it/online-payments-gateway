package org.fenixedu.onlinepaymentsgateway.api;

public enum OnlinePaymentOperationResultType {

    SUCCESS(true), RESULT_NOT_EXPECTED(false), ERROR_ON_PAYMENT_GATEWAY(false);

    private boolean success;

    private OnlinePaymentOperationResultType(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

}
